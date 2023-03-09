## QRCode
### QRCode is a Java web application that reads text from a QR code image using the zxing library. It provides a REST API that accepts a QR code image file and returns the text that it contains.

### Technologies Used
- Java
- Spring Boot
- Maven
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


