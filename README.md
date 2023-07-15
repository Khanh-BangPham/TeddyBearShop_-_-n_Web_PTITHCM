# I.Khảo sát và phân tích hệ thống:
## 1.Phân tích đề tài:
Xây dựng website bán gấu bông

## 2.Công cụ được sử dụng: 
  - Spring MVC, SQL Server
  - Hibernate, Validation, UploadFile, Interceptor, Email
  - HTML, CSS, Bootstrap và một vài thư viện được thiết kế sẵn.

## 3.Công cụ sử dụng:
  - Microsoft SQL Server 2019
  - Eclispe IDE

## 4.Phân quyền và chức năng:
  - Có 3 nhóm người dùng được phân quyền: Người dùng chưa có tài khoản, người dùng đã đăng ký tài khoản và người quản trị (admin)
  - Người dùng chưa có tài khoản:
    * Tìm kiếm, lọc sản phẩm
    * Xem chi tiết thông tin sản phẩm
    * Đăng ký tài khoản
  - Người dùng đã đăng ký tài khoản:
    * Các quyền như người dùng chưa có tài khoản đăng nhập
    * Đăng nhập
    * Đăng xuất
    * Thêm sản phẩm vào giỏ hàng
    * Làm thủ tục thanh toán (Checkout)
    * Sửa đổi thông tin cá nhân
    * Mục danh sách sản phẩm yêu thích
    * Xem lại đơn hàng cá nhân
  - Người quản trị (admin):
    * Đăng nhập vào tài khoản quản trị. (TK: admin, MK: 123456)
    * Tìm kiếm, lọc thông tin.
    * Có quyền thêm, sửa, xóa, xem chi tiết các thông tin sau: Danh mục sản phẩm, sản phẩm, nhà cung cấp, phương thức thanh toán, phương thức vận chuyển và tin tức.
    * Có quyền xem chi tiết các thông tin sau: Khách hàng, hóa đơn và đơn hàng chi tiết.

## 5.Phân tích hệ thống:
●Gồm 12 bảng:
  - QuanTri
  - SanPham - NhomSanPham - NhaCungCap
  - KhachHang - UaThich - DanhGia
  - DonHang - ChiTietDonHang - ThanhToan - VanChuyen
  - QuangCao


●Mô hình Diagram:

![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/251e04af-52c5-45e6-88bd-cb796538e975)

●Chi tiết các bảng: 

-QuanTri

![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/81dbda20-1202-49ba-bea8-9433e9fc58fa)

-SanPham
![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/3a07baba-2294-496b-8153-2aff1c7ad102)


-NhomSanPham
![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/8c18e8ef-c71d-4194-b1ac-19fe4ab01aa9)

-NhaCungCap
![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/79143e42-ec5c-4432-add6-8cd8203c2ed0)

-KhachHang
![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/f93a5e3a-6f6c-45ed-a9a0-a742e73e5899)

-UaThich

![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/985025d3-0db6-4177-a373-0078b0b2b858)


-DanhGia
![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/e63fd216-9546-47d4-a7d2-3984f9ccff35)

-DonHang

![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/d1ca0ef7-99f8-4df4-9c78-23a498016aba)

-ChiTietDonHang
![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/8d693c3c-7f9a-4fa4-86a1-090e97dd2696)

-ThanhToan

![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/417ca651-ff1c-422d-a51b-c15545722f14)

-VanChuyen

![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/8a65a582-8d0e-4e42-a56b-d9cc6752c06a)

-QuangCao

![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/70cadc92-d6e5-4cdf-9c7d-72416aff0885)


II. Giao diện:
1. Trang chủ:
![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/0c6c99ee-45dd-4eda-9a5b-a31cc27afbc7)

![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/34051ced-2f39-4509-8462-5f72d5b2db60)

![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/8b21cfb5-043e-4807-b7fd-0d384b22da00)

![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/c9e39e97-27e9-45ee-8669-2b6213a3211e)

2.Trang đăng nhập và đăng ký:
-        Trang đăng nhập đối với khách hàng:
![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/73be4054-ade6-4f84-81f3-ef313f3338ce)

-        Trang đăng ký đối với khách hàng:
![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/5a00e0a8-008e-422c-8141-b55804c63054)

-        Trang đăng nhập đối với admin:
![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/70e3b1c6-f4f7-4e8a-80ec-74136733e1ee)

3.Trang xem chi tiết sản phẩm:

 ![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/ec49d355-d659-44bb-8951-6e947a37a38f)

4.Trang giỏ hàng:

![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/f9a221d5-77e0-4ec9-ac97-4473a0dc469f)

![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/ee1971f6-de9e-4ea9-9651-de8489ec530d)

![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/0a52c200-7f04-44af-9af4-fe5556a3382a)

![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/5df3bd76-e515-4071-9abd-5b7eeffbe205)

5.Trang thanh toán:

![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/3dc3265f-d756-43df-9687-15b66aa45274)

![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/6f7704cb-5c94-47bc-9c65-224c86a8035f)

6.Trang tài khoản của tôi:
![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/20f41ba3-c7ef-4b0d-8f50-e92554e3f420)

7.Trang đơn hàng của tôi:
![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/8fe0bfdc-9e21-4b96-8278-e6785d70917f)

8.Trang yêu thích:
![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/a1dbd732-fb53-48d1-bac3-f127f1f9268e)

9.Trang quản lý danh mục sản phẩm:
![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/a9ef443c-6842-4af0-a8ad-382a798f5c60)

10.Trang quản lý sản phẩm:
![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/84ae02a1-6ba7-4387-8cce-dffec5ebae13)

11.Trang quản lý nhà cung cấp:
![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/104b59d5-5d2a-4874-b9a7-89e7dd5cb14f)

12.Trang quản lý khách hàng:
![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/daa340a3-4f22-41f2-9b28-bfaf5ff5fc85)

13.Trang quản lý hóa đơn:
![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/080537c0-90d3-447c-ac84-f877a746da13)

14.Trang quản lý chi tiết đơn hàng:
![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/57ee09cc-6504-4007-b052-75ae7b0defa5)

15.Trang quản lý phương thức thanh toán:
![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/6cdf0cd4-8575-4121-b594-789477584173)

16.Trang quản lý phương thức vận chuyển:
![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/52e2ab0d-003f-4218-aac2-2744013bfe50)

17.Trang quản lý tin tức:
![image](https://github.com/Khanh-BangPham/TeddyBearShop_Website_Project_PTITHCM/assets/77458357/61bfbd13-ebc8-4494-b5b5-e4dec9a2d51b)
