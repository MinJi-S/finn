**Finn**
----
This project was generated with SpringBoot 1.5.9.RELEASE. LGU+ 홈IoT 청약툴을 웹으로 개발(Angular + SpringBoot 공부용)

1. Maven 설치

###RESTful API 설계
* http://localhost:port/swagger-ui.html

| 컨트롤러 | 기능                          | HTTP Method | URI                          | Parameter                            | Response                |
|----------|-------------------------------|-------------|------------------------------|--------------------------------------|-------------------------|
| pvs      | 테이블 조회(CHS_DEVICE_MODEL) 　　　  　| GET         | /pvs/select_chs_device_model |                                      | List<ChsDeviceModelDto> |
| pvs      | 사용자 등록 쿼리 스크립트 요청 　　　 　| GET         | /pvs/query/user 　　　　　　 | ?one_id&subsNo&homeName&subsType&custNo&svcCode | String |
| pvs      | 단말 등록 쿼리 스크립트 요청   　　　 　| GET         | /pvs/query/device　　　　　　| ?modelNo&typeCode&mac&sn&homeCode&uuid&chsDeviceTypeLevel&deviceIdType| String |
| pvs      | 사용자/단말 초기화 쿼리 스크립트 요청   | GET         | /pvs/query/delete　　　　　　| ?homeCode&uuid                                   | String |
| Msg      | 디렉토리 및 파일 리스트 조회  | GET         | /msg/server/list             | ?info=[dir, ags, pas, iss, eps, etc] | List<String>            |
| Msg      | 전문 메시지 조회　　　　　　  | GET         | /msg/server/json             | ?info&filename　　　　　　　　 | String　　　            |