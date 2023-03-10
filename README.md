## QRCode
### QRCode is a Java web application that reads text from a QR code image using the zxing library. It provides a REST API that accepts a QR code image file and returns the text that it contains.

### Technologies Used
- Java
- Spring Boot
- Maven
- Springdoc-openapi-starter-webmvc-ui
- Mapstruct
- Postgres
- zxing library<p>
### Installation
Clone the repository:<p>
`git clone https://github.com/JuliaKull/QRCode.git`<p>
Navigate to the QRCode directory:<p>
`cd QRCode`<p>
Build the project:<p>
`mvn clean install`<p>

Run the project:

`mvn spring-boot:run`
### Usage
The QRCode application provides a REST API that accepts data to generate a QR code or returns the text that is contained in a QR code image file that was sent. You can use any tool that can send HTTP requests to the API, such as curl or Postman



![2023-03-10_11-06-36](https://user-images.githubusercontent.com/102060016/224259291-dcce68ed-65d6-460b-a6ba-5785ad0d9ce8.png)
![2023-03-10_11-06-03](https://user-images.githubusercontent.com/102060016/224259303-97306171-b831-4c19-9fa5-c743e64dba3a.png)
