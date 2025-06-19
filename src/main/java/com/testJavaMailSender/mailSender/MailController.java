package com.testJavaMailSender.mailSender;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.internet.MimeMessage;

@RestController
public class MailController {
    private final JavaMailSender mailSender;

    public MailController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RequestMapping("/send-email-with-html-body")
    public String sendEmailWithHTMLBody() {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("alonginus06@gmail.com"); // sửa lỗi chính tả ở gmail
            helper.setTo("thuy2122502@gmail.com");
            helper.setSubject("Thông báo từ Phòng Đào tạo - STU");

            String htmlContent = """
                     <!DOCTYPE html>
                    <html lang="vi">
                    <head>
                      <meta charset="UTF-8">
                      <title>Thông báo nhận học bổng</title>
                    </head>
                    <body>
                      <h1 style="text-align: center;">THÔNG BÁO</h1>
                      <p><strong>Kính gửi:</strong> Sinh viên Trường Đại học Công nghệ Sài Gòn (STU)</p>

                      <p>Phòng Đào tạo thông báo đến các sinh viên có tên trong <strong>danh sách nhận học bổng</strong> cần <strong>trực tiếp đến Phòng Đào tạo</strong> để nhận học bổng và hoàn tất các thủ tục liên quan.</p>

                      <p>Sinh viên có thể tra cứu <strong>danh sách nhận học bổng</strong> tại đường dẫn:
                        <a href="https://www.youtube.com/shorts/KTZTaexBwYo" target="_blank">Xem tại đây</a>
                      </p>

                      <p><strong>Thời gian tiếp sinh viên:</strong><br>
                      Từ thứ Hai đến thứ Sáu, trong giờ hành chính (7h30 - 11h30, 13h00 - 16h30)</p>

                      <p><strong>Địa điểm:</strong><br>
                      Phòng Đào tạo - Trường Đại học Công nghệ Sài Gòn (STU)</p>

                      <p><strong>Lưu ý:</strong><br>
                      Sinh viên cần mang theo thẻ sinh viên hoặc giấy tờ tùy thân có ảnh khi đến nhận học bổng.</p>

                      <p>Hướng dẫn các bước nhận học bổng:
                        <a href="https://www.youtube.com/shorts/KTZTaexBwYo" target="_blank">Xem tại đây</a>
                      </p>

                      <p>Mọi thắc mắc vui lòng liên hệ Phòng Đào tạo qua email:
                        <a href="mailto:https://www.youtube.com/shorts/KTZTaexBwYo">daotao@stu.edu.vn</a>
                      </p>

                      <p>Đề nghị các bạn sinh viên sắp xếp thời gian đến nhận học bổng trong thời gian sớm nhất để đảm bảo quyền lợi cá nhân.</p>

                      <p style="text-align: right;"><em>Phòng Đào tạo</em><br>STU</p>
                    </body>
                    </html>


                    """;

            helper.setText(htmlContent, true); // true = gửi với nội dung HTML

            mailSender.send(message);
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }
}
