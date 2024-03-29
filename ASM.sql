USE [master]
GO
/****** Object:  Database [AsmJava4]    Script Date: 16/06/2022 03:08:15 CH ******/
CREATE DATABASE [AsmJava4]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'AsmJava4', FILENAME = N'E:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\AsmJava4.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'AsmJava4_log', FILENAME = N'E:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\AsmJava4_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [AsmJava4] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [AsmJava4].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [AsmJava4] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [AsmJava4] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [AsmJava4] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [AsmJava4] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [AsmJava4] SET ARITHABORT OFF 
GO
ALTER DATABASE [AsmJava4] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [AsmJava4] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [AsmJava4] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [AsmJava4] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [AsmJava4] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [AsmJava4] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [AsmJava4] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [AsmJava4] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [AsmJava4] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [AsmJava4] SET  DISABLE_BROKER 
GO
ALTER DATABASE [AsmJava4] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [AsmJava4] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [AsmJava4] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [AsmJava4] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [AsmJava4] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [AsmJava4] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [AsmJava4] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [AsmJava4] SET RECOVERY FULL 
GO
ALTER DATABASE [AsmJava4] SET  MULTI_USER 
GO
ALTER DATABASE [AsmJava4] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [AsmJava4] SET DB_CHAINING OFF 
GO
ALTER DATABASE [AsmJava4] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [AsmJava4] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [AsmJava4] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [AsmJava4] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'AsmJava4', N'ON'
GO
ALTER DATABASE [AsmJava4] SET QUERY_STORE = OFF
GO
USE [AsmJava4]
GO
/****** Object:  Table [dbo].[history]    Script Date: 16/06/2022 03:08:15 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[history](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[userId] [int] NOT NULL,
	[videoId] [int] NOT NULL,
	[viewsDate] [datetime] NOT NULL,
	[isLiked] [bit] NOT NULL,
	[likeDate] [datetime] NULL,
 CONSTRAINT [PK_history] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user]    Script Date: 16/06/2022 03:08:16 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[email] [varchar](50) NOT NULL,
	[isAdmin] [bit] NOT NULL,
	[isActive] [bit] NOT NULL,
 CONSTRAINT [PK_user] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[video]    Script Date: 16/06/2022 03:08:16 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[video](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](250) NOT NULL,
	[href] [varchar](50) NOT NULL,
	[poster] [varchar](250) NULL,
	[views] [int] NOT NULL,
	[shares] [int] NOT NULL,
	[description] [nvarchar](250) NULL,
	[isActive] [bit] NOT NULL,
 CONSTRAINT [PK_video] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[history] ON 

INSERT [dbo].[history] ([id], [userId], [videoId], [viewsDate], [isLiked], [likeDate]) VALUES (1, 2, 1, CAST(N'2022-04-18T20:53:39.770' AS DateTime), 0, NULL)
INSERT [dbo].[history] ([id], [userId], [videoId], [viewsDate], [isLiked], [likeDate]) VALUES (2, 2, 3, CAST(N'2022-04-07T14:17:12.217' AS DateTime), 0, NULL)
INSERT [dbo].[history] ([id], [userId], [videoId], [viewsDate], [isLiked], [likeDate]) VALUES (11, 9, 1, CAST(N'2022-04-19T19:49:59.433' AS DateTime), 0, NULL)
INSERT [dbo].[history] ([id], [userId], [videoId], [viewsDate], [isLiked], [likeDate]) VALUES (12, 1, 2, CAST(N'2022-04-19T20:38:44.600' AS DateTime), 0, NULL)
INSERT [dbo].[history] ([id], [userId], [videoId], [viewsDate], [isLiked], [likeDate]) VALUES (13, 1, 1, CAST(N'2022-04-19T20:38:48.973' AS DateTime), 1, CAST(N'2022-04-21T23:12:51.210' AS DateTime))
INSERT [dbo].[history] ([id], [userId], [videoId], [viewsDate], [isLiked], [likeDate]) VALUES (17, 9, 3, CAST(N'2022-04-22T00:00:48.360' AS DateTime), 0, NULL)
INSERT [dbo].[history] ([id], [userId], [videoId], [viewsDate], [isLiked], [likeDate]) VALUES (18, 10, 1, CAST(N'2022-04-22T07:39:59.647' AS DateTime), 0, NULL)
INSERT [dbo].[history] ([id], [userId], [videoId], [viewsDate], [isLiked], [likeDate]) VALUES (19, 10, 2, CAST(N'2022-04-22T07:40:28.370' AS DateTime), 1, CAST(N'2022-04-22T07:40:31.860' AS DateTime))
SET IDENTITY_INSERT [dbo].[history] OFF
GO
SET IDENTITY_INSERT [dbo].[user] ON 

INSERT [dbo].[user] ([id], [username], [password], [email], [isAdmin], [isActive]) VALUES (1, N'thanh', N'123', N'thanhle.08072000@gmail.com', 1, 1)
INSERT [dbo].[user] ([id], [username], [password], [email], [isAdmin], [isActive]) VALUES (2, N'leminh', N'123', N'minh@gmail.com', 0, 1)
INSERT [dbo].[user] ([id], [username], [password], [email], [isAdmin], [isActive]) VALUES (9, N'congthanh', N'123', N'thanhle.221001@gmail.com', 0, 1)
INSERT [dbo].[user] ([id], [username], [password], [email], [isAdmin], [isActive]) VALUES (10, N'thanh22', N'123', N'thanhle.221001@gmail.com', 0, 1)
SET IDENTITY_INSERT [dbo].[user] OFF
GO
SET IDENTITY_INSERT [dbo].[video] ON 

INSERT [dbo].[video] ([id], [title], [href], [poster], [views], [shares], [description], [isActive]) VALUES (1, N'EM ĐÃ XA ANH REMIX | Em Đã Xa Anh Vào Trong Một Chiều Mưa Bay Remix - NHƯ VIỆT | BIBO REMIX', N'A5_Ygd529qg', N'https://img.youtube.com/vi/A5_Ygd529qg/maxresdefault.jpg', 4, 0, N'EM ĐÃ XA ANH REMIX | Em Đã Xa Anh Vào Trong Một Chiều Mưa Bay Remix - NHƯ VIỆT | BIBO REMIXxx', 1)
INSERT [dbo].[video] ([id], [title], [href], [poster], [views], [shares], [description], [isActive]) VALUES (2, N'Chỉ Muốn Bên Em Thật Gần (Lofi)', N'w2j_RyfUVEI', N'https://img.youtube.com/vi/w2j_RyfUVEI/maxresdefault.jpg', 5, 0, N'Provided to YouTube by Believe SAS

Chỉ Muốn Bên Em Thật Gần (Lofi) · YLing · Star Online · Nguyễn Minh Phúc · Nguyễn Minh Phúc', 1)
INSERT [dbo].[video] ([id], [title], [href], [poster], [views], [shares], [description], [isActive]) VALUES (3, N'W/n - ‘3107’ full album| ft. ( titie, Nâu ,Dươngg )', N'GatNL0mmQGc', N'https://img.youtube.com/vi/GatNL0mmQGc/maxresdefault.jpg', 2, 0, N'Cảm ơn nâu , titie và dương đã hỗ trợ và giúp ep 3107 của mình lần này được hoàn thiện ****
31071 - 31072 - 31073 lyric video', 1)
INSERT [dbo].[video] ([id], [title], [href], [poster], [views], [shares], [description], [isActive]) VALUES (5, N'TÌNH ĐẸP ĐẾN MẤY CŨNG TÀN - NHƯ VIỆT | OFFICIAL MUSIC VIDEO', N'c5llVZ79WlI', N'https://img.youtube.com/vi/c5llVZ79WlI/maxresdefault.jpg', 12, 0, N'TÌNH ĐẸP ĐẾN MẤY CŨNG TÀN - NHƯ VIỆT | OFFICIAL MUSIC VIDEO', 1)
INSERT [dbo].[video] ([id], [title], [href], [poster], [views], [shares], [description], [isActive]) VALUES (6, N'Em Ổn Không - Trịnh Thiên Ân x ViruSs x Láo Soái Nhi「Lyrics Video」', N'vYLYQY5dMiw', N'https://img.youtube.com/vi/vYLYQY5dMiw/maxresdefault.jpg', 5, 0, N'Em Ổn Không - Trịnh Thiên Ân x ViruSs x Láo Soái Nhi 「Lyrics Video」
➨Link Gốc MV: https://youtu.be/uJ7iRK1rF_E
► Song : Em Ổn Không
► Singer : Trịnh Thiên Ân
► Nhạc hoa lời việt: Viruss
► Cast : Láo Soái Nhi', 1)
INSERT [dbo].[video] ([id], [title], [href], [poster], [views], [shares], [description], [isActive]) VALUES (7, N'Chắc Vì Mình Chưa Tốt (Lofi Ver.) - Thanh Hưng x Vux', N'hmPsclre6BQ', N'https://img.youtube.com/vi/xASpHHL7SxI/maxresdefault.jpg', 3, 0, N'Chắc Vì Mình Chưa Tốt (Lofi Ver.) - Thanh Hưng x Vux', 1)
INSERT [dbo].[video] ([id], [title], [href], [poster], [views], [shares], [description], [isActive]) VALUES (8, N'Đào Nương (Lofi Ver) - Hoàng Vương x Thành Acoustic x Liam Lofi', N't5zPYuo310M', N'https://img.youtube.com/vi/t5zPYuo310M/maxresdefault.jpg', 2, 0, N'♬ Đào Nương (Lofi Ver) - Hoàng Vương x Thành Acoustic x Liam Lofi

#DaoNuong #HoangVuong #Liam', 1)
INSERT [dbo].[video] ([id], [title], [href], [poster], [views], [shares], [description], [isActive]) VALUES (17, N'♬ NHỚ NGƯỜI HAY NHỚ - SOFIA X KHÓI X CHÂU ĐĂNG KHOA ( MẠNH LTK REMIX )', N'G19SI86Y3ys', N'https://img.youtube.com/vi/G19SI86Y3ys/maxresdefault.jpg', 4, 0, N'NHỚ NGƯỜI HAY NHỚ - SOFIA X KHÓI X CHÂU ĐĂNG KHOA ( MẠNH LTK REMIX )
MV Gốc: https://youtu.be/QDJgzJVVE2Y 
NCT: https://www.nhaccuatui.com/bai-hat/nh...
#NhoNguoiHayNho #Sofia #Khoi #ChauDangKhoa #ManhLTK  #HoaHongDaiMusic ', 1)
INSERT [dbo].[video] ([id], [title], [href], [poster], [views], [shares], [description], [isActive]) VALUES (29, N'Lofi Lyrics/Phận Duyên Lỡ Làng - Phát Huy T4 x Truzg x meChill - Kho Nhạc Lofi Chill Nhất TikTok', N'JsVoZlLxRqY', N'https://img.youtube.com/vi/JsVoZlLxRqY/maxresdefault.jpg', 5, 0, N'♬ Lofi Lyrics/Phận Duyên Lỡ Làng - Phát Huy T4 x Truzg x meChill - Kho Nhạc Lofi Chill Nhất TikTok
🎬 Official MV: https://youtu.be/aMMZRIU46lI
♬ Lyrics:', 1)
INSERT [dbo].[video] ([id], [title], [href], [poster], [views], [shares], [description], [isActive]) VALUES (30, N'LÀM GÌ CHỈ VỚI 1 TAY? | ACTION C', N'8O61BnvtrvQ', N'https://img.youtube.com/vi/8O61BnvtrvQ/maxresdefault.jpg', 4, 0, N'Bạn sẽ làm gì để giải trí với 1 tay nếu tay còn lại đang bận làm việc khác?
ĐỪNG LO! ĐÃ CÓ TRẤN MA AFK - GIÚP BẠN THỐNG TRỊ TAM GIỚI CHỈ VỚI MỘT TAY ĐẾN DƯƠNG QUÁ CÒN PHẢI NỂ!', 1)
INSERT [dbo].[video] ([id], [title], [href], [poster], [views], [shares], [description], [isActive]) VALUES (31, N'Kara Lyrics | Dạ Vũ – Tăng Duy Tân | Nhạc Hot TikTok | Lyrics Video', N'sAqMeZEC224', N'https://img.youtube.com/vi/G19SI86Y3ys/maxresdefault.jpg', 0, 0, N'Kara Lyrics | Dạ Vũ – Tăng Duy Tân | Nhạc Hot TikTok | Lyrics:

Khi màn đêm vừa buông, mưa vừa tuôn ở trên mái hiên, em vẫn cứ uống.
Sương còn vương hàng trăm nỗi đau như kéo em xuống, nơi vực sâu tận cùng trái tim khô cằn sỏi đá.', 1)
SET IDENTITY_INSERT [dbo].[video] OFF
GO
ALTER TABLE [dbo].[history] ADD  CONSTRAINT [DF_history_viewsDate]  DEFAULT (getdate()) FOR [viewsDate]
GO
ALTER TABLE [dbo].[history] ADD  CONSTRAINT [DF_history_isLiked]  DEFAULT ((0)) FOR [isLiked]
GO
ALTER TABLE [dbo].[user] ADD  CONSTRAINT [DF_user_isAdmin]  DEFAULT ((0)) FOR [isAdmin]
GO
ALTER TABLE [dbo].[user] ADD  CONSTRAINT [DF_user_isActive]  DEFAULT ((1)) FOR [isActive]
GO
ALTER TABLE [dbo].[video] ADD  CONSTRAINT [DF_video_views]  DEFAULT ((0)) FOR [views]
GO
ALTER TABLE [dbo].[video] ADD  CONSTRAINT [DF_video_shares]  DEFAULT ((0)) FOR [shares]
GO
ALTER TABLE [dbo].[video] ADD  CONSTRAINT [DF_video_isActive]  DEFAULT ((1)) FOR [isActive]
GO
ALTER TABLE [dbo].[history]  WITH CHECK ADD  CONSTRAINT [FK_history_user] FOREIGN KEY([userId])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[history] CHECK CONSTRAINT [FK_history_user]
GO
ALTER TABLE [dbo].[history]  WITH CHECK ADD  CONSTRAINT [FK_history_video] FOREIGN KEY([videoId])
REFERENCES [dbo].[video] ([id])
GO
ALTER TABLE [dbo].[history] CHECK CONSTRAINT [FK_history_video]
GO
/****** Object:  StoredProcedure [dbo].[sp_selectUserIsLikeVideoByVideoHref]    Script Date: 16/06/2022 03:08:16 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[sp_selectUserIsLikeVideoByVideoHref](@videoHref varchar(50))
as begin
	select
		u.id, u.username, u.[password], u.email, u.isAdmin, u.isActive
	from 
		[user] u inner join history h on u.id = h.userId
			inner join video v on h.videoId = v.id
	where
		v.href = @videoHref and u.isActive = 1 and v.isActive = 1 and h.isLiked = 1
end
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'history', @level2type=N'COLUMN',@level2name=N'viewsDate'
GO
USE [master]
GO
ALTER DATABASE [AsmJava4] SET  READ_WRITE 
GO
