package pd04359.dao;

import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import pd04359.until.JpaUtil;

public class AbstractDao<T> {
	public static final EntityManager entityManager = JpaUtil.getEntityManager();
	
	public T findById(Class<T> clazz, Integer id){
		return entityManager.find(clazz, id);
	}
	
	//trả về list các thực thể có trong bảng (T)
	public List<T> findAll(Class<T> clazz, boolean existIsActive){
		//SELECT o FROM entity o WHERE o.isActive = 1;
		//Tuy vấn các user đang còn hoạt động, 1 là còn hoạt động, 0 là đã ngừng hoạt động
		String entityName = clazz.getSimpleName();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT o FROM ").append(entityName).append(" o");
		if(existIsActive == true) {
			sql.append(" WHERE o.isActive = 1");
		}
		TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
		return query.getResultList();
		
	}
	
	//trả về list các thực thể có trong bảng (T) có thêm phân trang
	public List<T> findAll(Class<T> clazz, boolean existIsActive, int pageNumber, int pageSize){
		//SELECT o FROM entity WHERE isActive = 1;
		//Tuy vấn các user đang còn hoạt động, 1 là còn hoạt động, 0 là đã ngừng hoạt động
		
		String entityName = clazz.getSimpleName();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT o FROM ").append(entityName).append(" o");
		if(existIsActive == true) {
			sql.append(" WHERE isActive = 1");
		}
		TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
		query.setFirstResult((pageNumber - 1  )* pageSize);
		query.setMaxResults(pageSize);
		/* giả sử có 5 phần tử muốn chia 1 trang chưa 2 phấn tử
		 * --> tổng số trang là 3
		 * 	trang 1: [0], [1]
		 * 	trang 2: [2], [3]
		 * 	trang 3: [4]
		 *  muốn lấy phần tử ở trang 2 >> ((pageNumber - 1  )* pageSize) 
		 *  = (2 -1) * 1 = 2 >> bắt đầu lấy từ vị trí thứ 2 và lấy 2 phần tử
		 * */
		return query.getResultList();
		
	}
	
	
	//select o fromfyuser o where o.username = ?0 and o.password = ?1;
	public T findOne(Class<T> clazz, String sql, Object... params) {
		TypedQuery<T> query = entityManager.createQuery(sql, clazz);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		
		//Nếu để T r = query.getSingleResult(); 
		//mà khi truy vấn không trả về 1 kết quả nào hệ thống sẽ báo lỗi ngay lập tức
		List<T> result = query.getResultList();
		if(result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}
	
	public List<T> findMany(Class<T> clazz, String sql, Object... params) {
		TypedQuery<T> query = entityManager.createQuery(sql, clazz);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findManyNativeQuery( String sql, Object... params) {
		Query query = entityManager.createNativeQuery(sql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.getResultList();
	}
	
	public T create(T entity) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			System.out.println("Create success");
			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Can not create entity " +
			entity.getClass().getSimpleName() + " to database");
			throw new RuntimeException(e);
		}
	}
	
	public T update(T entity) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
			System.out.println("Update success");
			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Can not update entity " +
			entity.getClass().getSimpleName());
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public T delete(T entity) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(entity);
			entityManager.getTransaction().commit();
			System.out.println("Delete success");
			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Can not remove entity " +
			entity.getClass().getSimpleName());
			throw new RuntimeException(e);
		}
	}
	
	public List<T> callStored(String namedStored, Map<String, Object> params){
		StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery(namedStored);
		params.forEach((key, value) -> query.setParameter(key, value));
		return (List<T>) query.getResultList();
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	protected void finalize() throws Throwable {
		entityManager.close();
		super.finalize();
	}
	
}
