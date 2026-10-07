# 네트워킹 (Networking)

백엔드 인터뷰를 위한 네트워킹 핵심 개념 정리입니다.

## 학습 순서
1. OSI 7계층과 TCP/IP 모델
2. HTTP/HTTPS
3. TCP와 UDP
4. DNS
5. 로드 밸런싱과 프록시

---

## 1. OSI 7계층과 TCP/IP 모델

### OSI 7계층
| 계층 | 이름 | 역할 | 프로토콜/장비 |
|------|------|------|---------------|
| 7 | Application | 사용자 인터페이스 | HTTP, FTP, SMTP |
| 6 | Presentation | 데이터 표현/암호화 | SSL/TLS, JPEG |
| 5 | Session | 세션 관리 | NetBIOS |
| 4 | Transport | 종단 간 통신 | TCP, UDP |
| 3 | Network | 라우팅, 주소 지정 | IP, ICMP, Router |
| 2 | Data Link | 프레임 전송, 에러 검출 | Ethernet, Switch |
| 1 | Physical | 물리적 전송 | Cable, Hub |

### TCP/IP 4계층
- **Application** (응용): OSI 5~7계층
- **Transport** (전송): TCP, UDP
- **Internet** (인터넷): IP, ICMP, ARP
- **Network Access** (네트워크 접근): OSI 1~2계층

---

## 2. HTTP/HTTPS

### HTTP (HyperText Transfer Protocol)
- **특징**:
  - 무상태(Stateless): 각 요청이 독립적
  - 비연결성(Connectionless): 요청/응답 후 연결 종료
  - 텍스트 기반 프로토콜

### HTTP 메서드
- **GET**: 리소스 조회
- **POST**: 리소스 생성
- **PUT**: 리소스 전체 수정
- **PATCH**: 리소스 부분 수정
- **DELETE**: 리소스 삭제
- **HEAD**: GET과 동일하지만 헤더만 반환
- **OPTIONS**: 지원하는 메서드 확인

### HTTP 상태 코드
- **1xx (정보)**: 요청 처리 중
- **2xx (성공)**:
  - 200 OK
  - 201 Created
  - 204 No Content
- **3xx (리다이렉션)**:
  - 301 Moved Permanently
  - 302 Found (임시 이동)
  - 304 Not Modified
- **4xx (클라이언트 오류)**:
  - 400 Bad Request
  - 401 Unauthorized
  - 403 Forbidden
  - 404 Not Found
- **5xx (서버 오류)**:
  - 500 Internal Server Error
  - 502 Bad Gateway
  - 503 Service Unavailable

### HTTPS (HTTP Secure)
- **특징**: SSL/TLS로 암호화된 HTTP
- **과정**:
  1. Client Hello (지원 암호화 방식 전송)
  2. Server Hello (인증서 전송)
  3. 인증서 검증
  4. 세션 키 생성 및 교환
  5. 암호화 통신 시작

### HTTP/1.1 vs HTTP/2 vs HTTP/3
| 특징 | HTTP/1.1 | HTTP/2 | HTTP/3 |
|------|----------|---------|---------|
| 연결 | 하나의 요청/응답 | 멀티플렉싱 | QUIC |
| 헤더 | 텍스트 | 압축 (HPACK) | 압축 (QPACK) |
| 전송 프로토콜 | TCP | TCP | UDP |
| HOL Blocking | 있음 | 있음 (TCP 레벨) | 없음 |

---

## 3. TCP와 UDP

### TCP (Transmission Control Protocol)
- **특징**:
  - 연결 지향 (Connection-oriented)
  - 신뢰성 보장 (재전송, 순서 보장)
  - 흐름 제어, 혼잡 제어
  - 전이중(Full-duplex), 점대점(Point-to-Point)

#### 3-way Handshake (연결)
1. **SYN**: 클라이언트 → 서버 (연결 요청)
2. **SYN + ACK**: 서버 → 클라이언트 (수락)
3. **ACK**: 클라이언트 → 서버 (확인)

#### 4-way Handshake (종료)
1. **FIN**: 클라이언트 → 서버
2. **ACK**: 서버 → 클라이언트
3. **FIN**: 서버 → 클라이언트
4. **ACK**: 클라이언트 → 서버

### UDP (User Datagram Protocol)
- **특징**:
  - 비연결성 (Connectionless)
  - 신뢰성 보장 없음
  - 빠른 전송
  - 브로드캐스트/멀티캐스트 가능
- **사용 예**: 실시간 스트리밍, DNS, VoIP

### TCP vs UDP
| 구분 | TCP | UDP |
|------|-----|-----|
| 연결 | 연결 지향 | 비연결 |
| 신뢰성 | 보장 | 보장 안 함 |
| 순서 | 보장 | 보장 안 함 |
| 속도 | 느림 | 빠름 |
| 용도 | HTTP, FTP, 이메일 | 스트리밍, DNS |

---

## 4. DNS (Domain Name System)

### DNS란?
- 도메인 이름을 IP 주소로 변환하는 시스템
- 분산 계층 구조

### DNS 조회 과정
1. 브라우저 캐시 확인
2. OS 캐시 확인
3. 로컬 DNS 서버(ISP) 조회
4. Root DNS 서버 조회
5. TLD DNS 서버 조회 (.com, .net 등)
6. Authoritative DNS 서버 조회
7. IP 주소 반환

### DNS 레코드 타입
- **A**: 도메인 → IPv4 주소
- **AAAA**: 도메인 → IPv6 주소
- **CNAME**: 도메인 별칭
- **MX**: 메일 서버
- **NS**: 네임 서버
- **TXT**: 텍스트 정보

---

## 5. 로드 밸런싱과 프록시

### 로드 밸런서 (Load Balancer)
- **역할**: 트래픽을 여러 서버에 분산
- **알고리즘**:
  - **Round Robin**: 순차적으로 분배
  - **Least Connections**: 연결 수가 적은 서버로
  - **IP Hash**: IP 주소 해싱
  - **Weighted Round Robin**: 가중치 기반

### L4 vs L7 로드 밸런서
| 구분 | L4 (Transport) | L7 (Application) |
|------|----------------|------------------|
| 계층 | TCP/UDP | HTTP/HTTPS |
| 기준 | IP, Port | URL, Header, Cookie |
| 속도 | 빠름 | 느림 |
| 기능 | 단순 분산 | 콘텐츠 기반 라우팅 |

### 프록시 (Proxy)
#### Forward Proxy
- 클라이언트 대신 요청
- 캐싱, 익명화, 접근 제어

#### Reverse Proxy
- 서버 대신 응답
- 로드 밸런싱, SSL 종료, 캐싱
- 예: Nginx, HAProxy

---

## 추가 주요 개념

### REST (Representational State Transfer)
- **원칙**:
  1. Client-Server 구조
  2. Stateless (무상태)
  3. Cacheable (캐시 가능)
  4. Layered System (계층화)
  5. Uniform Interface (일관된 인터페이스)
  6. Code on Demand (선택적)

### WebSocket
- 양방향 실시간 통신
- HTTP로 핸드셰이크 후 TCP 연결 유지
- 채팅, 실시간 알림에 사용

### CORS (Cross-Origin Resource Sharing)
- 다른 도메인의 리소스 요청 허용
- Preflight Request (OPTIONS)로 확인

---

## 인터뷰 대비 핵심 질문

1. **TCP와 UDP의 차이는?**
2. **3-way handshake 과정을 설명하세요.**
3. **HTTP와 HTTPS의 차이는?**
4. **HTTP/1.1과 HTTP/2의 차이는?**
5. **DNS 조회 과정을 설명하세요.**
6. **L4 로드 밸런서와 L7 로드 밸런서의 차이는?**
7. **RESTful API란 무엇인가?**
8. **CORS가 필요한 이유는?**
9. **쿠키와 세션의 차이는?**
10. **GET과 POST의 차이는?**

---

## 더 공부할 주제
- [ ] CDN (Content Delivery Network)
- [ ] NAT (Network Address Translation)
- [ ] VPN (Virtual Private Network)
- [ ] gRPC
- [ ] GraphQL
- [ ] API Gateway
