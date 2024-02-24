# Magic-Post
## Back-end chạy trước
### Chuẩn bị:
* java >= 17
* Nếu IDE là Intellij IDEA, phiên bản >= 2021.3; đối với IDE khác, hãy kiểm tra xem IDE đó có build được code java 17 không
* Maven; kiểm tra các Path variable
* Khởi động XAMP (Apache, mySql)
* Vào file application.properties, chỉnh sửa password, port nếu cần
* Mở phpMyAdmin, chạy lệnh: create database magicpost
* Chạy file MagicPostApplication.java
### Test back-end API
* Dùng Postman, API = "http://localhost:8080/" + path 
* Hiện có các (path) API sau: (đang để method tất cả là Post)
```
Vào Postman để xem chi tiết input, output

```
### Mô tả cở sở dữ liệu
Mô tả ngắn gọn về cách lưu trữ thông tin đơn hàng: 
* Kiện hàng tương ứng gói hàng ngoài đời, 1 kiện hàng có thể gồm nhiều thứ: 10 cuốn sách, 2 cái smartphone, v.v
* Mỗi đơn hàng sẽ là 1 kiện hàng (ngoài đời có thể sẽ có những chuyến (trip) mà 1 lần 3 kiện hàng, tạm thời ta sẽ xem đó là 3 đơn hàng khác nhau và cùng điểm đi điểm đến)
* Đơn hàng từ khách hàng đến điểm giao dịch (có thể là từ người gửi hoặc người nhận) được lưu trong bảng "van_chuyen_khach_hang_giao_dich"
* Tương tự với giao dịch - tập kết, tập kết - tập kết
* Kiện hàng 'tồn kho' ở điểm giao dịch thì được lưu trong bảng "hang_kho_giao_dich"
* Tương tự hàng tồn kho tập kết
### Nguyễn Viết Mạnh:
* Sử dụng annotation @ (Entity, Service, v.v) để springboot tự động tạo object
* Folder model: mỗi file trong thư mục folder tương ứng 1 bảng trong csdl mySql
* Folder repository: mỗi file trong này là 1 interface, dùng để truy vấn csdl. Viết hàm theo đúng pháp, springboot sẽ tự động nhận diện và tạo hàm
* Folder DTO: phía client, muốn thêm dữ liệu vào database (ví dụ như thêm đơn hàng, v.v), ta sử dụng DTO (Data Transfer Object) để chứa biểu mẫu và chuyển lên controller. Kết hợp với 1 số thuộc tính phụ mà phía client không biết, controller sẽ tạo ra model tương ứng.  
Ngoài ra, DTO còn dùng cần giấu bớt 1 số thông tin không cần thiết (vd: id, mật khẩu, v.v) khi hiển thị phía client.
* Folder service: sử dụng annotation @AutoWired, springboot sẽ tạo object
* Folder controller: đây là nơi tạo API, gồm các annotation như @ RequestMapping, GetMapping, PostMapping. Kết quả của API nên là ResponseEntity<> để đính kèm thêm HttpStatus (OK, 404, v.v)
## Front-end chạy sau
