# Swagger 2 Example

Spring WebMVC를 이용해서 만든 REST API와 Swagger 2(정확히는 Swagger 2를 Spring Framework에서 사용하기 쉽게 Wrapping된 Springfox)의 연동 예제.

Java Config 예제가 아닌 XML Namespace 예제이지만, Springfox는 완전히 XML 설정을 지원하지 않기 때문에 Swagger 설정을 위한 Java Config 클래스를 만들고, 이 클래스를 스프링 bean 설정 XML 파일에 bean으로써 추가했다.

이 프로젝트는 아래 문서를 참고해서 만들어졌다.

  * [Spring REST + Swagger 2 Integration with Annotation + XML Example](https://www.concretepage.com/spring-4/spring-rest-swagger-2-integration-with-annotation-xml-example)

## 구동방법

톰캣 등의 서블릿 컨테이너에 배포한 후, `/swagger-ui.html` 로 접속한다.
