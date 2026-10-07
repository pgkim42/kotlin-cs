# 시스템 디자인 (System Design)

백엔드 인터뷰를 위한 시스템 디자인 핵심 개념 정리입니다.

## 학습 순서
1. 시스템 디자인 접근 방법
2. 확장성 (Scalability)
3. 가용성과 안정성
4. 캐싱 전략
5. 메시지 큐와 이벤트 드리븐
6. 실전 디자인 예제

---

## 1. 시스템 디자인 접근 방법

### 4단계 접근법

#### 1단계: 요구사항 파악
- **기능적 요구사항**: 시스템이 해야 할 일
- **비기능적 요구사항**: 성능, 확장성, 가용성
- **제약사항**: 트래픽, 저장 용량, 레이턴시

#### 2단계: 개략적 설계
- 주요 컴포넌트 식별
- API 설계
- 데이터 모델 설계
- High-level 아키텍처 다이어그램

#### 3단계: 상세 설계
- 병목 지점 파악 및 해결
- 확장성 고려
- 데이터 파티셔닝
- 캐싱 전략

#### 4단계: 마무리
- 장애 시나리오
- 모니터링과 알림
- 추가 고려사항

---

## 2. 확장성 (Scalability)

### 수직 확장 (Scale-Up)
- **정의**: 서버 사양 향상 (CPU, RAM 증가)
- **장점**: 구현 간단
- **단점**: 한계 존재, 비용 증가, 단일 장애점

### 수평 확장 (Scale-Out)
- **정의**: 서버 대수 증가
- **장점**: 무한 확장 가능, 장애 대응
- **단점**: 복잡도 증가, 데이터 일관성

### 로드 밸런싱
- **역할**: 트래픽 분산
- **알고리즘**:
  - Round Robin
  - Least Connections
  - IP Hash
  - Weighted Round Robin
- **헬스 체크**: 장애 서버 자동 제외

### 데이터베이스 확장

#### Replication (복제)
- **Master-Slave**: 읽기 분산
  - Master: 쓰기 전용
  - Slave: 읽기 전용
- **Master-Master**: 쓰기 분산

#### Sharding (샤딩)
- **수평 파티셔닝**: 데이터를 여러 DB에 분산
- **샤딩 전략**:
  - Range-based: 범위로 분할
  - Hash-based: 해시로 분할
  - Directory-based: 룩업 테이블 사용
- **문제점**:
  - 조인 어려움
  - 재샤딩 복잡
  - 데이터 불균형

---

## 3. 가용성과 안정성

### 가용성 (Availability)
- **정의**: 시스템이 정상 동작하는 시간 비율
- **측정**: Uptime / (Uptime + Downtime)
- **목표**:
  - 99.9% (3 nines): 연간 8.76시간 다운타임
  - 99.99% (4 nines): 연간 52.56분
  - 99.999% (5 nines): 연간 5.26분

### 장애 대응 (Fault Tolerance)
- **중복성 (Redundancy)**: 여러 서버, 여러 데이터센터
- **페일오버 (Failover)**: 자동 장애 전환
- **서킷 브레이커 (Circuit Breaker)**: 장애 전파 방지

### 백업과 복구
- **백업 전략**:
  - Full Backup: 전체 백업
  - Incremental Backup: 증분 백업
  - Differential Backup: 차등 백업
- **RPO (Recovery Point Objective)**: 복구 시점 목표
- **RTO (Recovery Time Objective)**: 복구 시간 목표

---

## 4. 캐싱 전략

### 캐시 레벨
1. **CDN**: 정적 콘텐츠 (이미지, CSS, JS)
2. **애플리케이션 캐시**: Redis, Memcached
3. **데이터베이스 캐시**: 쿼리 캐시

### 캐싱 전략

#### Cache-Aside (Lazy Loading)
1. 캐시 확인
2. 없으면 DB 조회
3. 캐시에 저장

#### Write-Through
- 쓰기 시 캐시와 DB 동시 업데이트
- 일관성 보장, 쓰기 지연

#### Write-Behind (Write-Back)
- 캐시에만 쓰기, 비동기로 DB 업데이트
- 쓰기 성능 향상, 데이터 유실 위험

#### Refresh-Ahead
- 만료 전에 미리 갱신

### 캐시 무효화
- **TTL (Time To Live)**: 만료 시간
- **LRU (Least Recently Used)**: 가장 오래 사용되지 않은 항목 제거
- **LFU (Least Frequently Used)**: 사용 빈도 낮은 항목 제거

### 캐시 문제
- **Cache Stampede**: 동시에 많은 요청이 캐시 미스
  - 해결: Locking, Refresh-Ahead
- **Thundering Herd**: 동시 요청으로 서버 과부하

---

## 5. 메시지 큐와 이벤트 드리븐

### 메시지 큐 (Message Queue)
- **역할**: 비동기 통신, 작업 분산
- **장점**:
  - 느슨한 결합 (Decoupling)
  - 부하 분산
  - 장애 격리
- **예**: RabbitMQ, Apache Kafka, AWS SQS

### 메시징 패턴
- **Point-to-Point**: 하나의 수신자
- **Pub/Sub**: 여러 구독자

### Apache Kafka
- **특징**:
  - 고성능 분산 스트리밍 플랫폼
  - 영구 저장
  - 순서 보장
- **구성**:
  - Producer: 메시지 생성
  - Broker: 메시지 저장
  - Consumer: 메시지 소비
  - Topic: 메시지 분류
  - Partition: 병렬 처리

### 이벤트 드리븐 아키텍처
- **이벤트 소싱**: 상태 변경을 이벤트로 저장
- **CQRS**: 읽기와 쓰기 모델 분리

---

## 6. 실전 디자인 예제

### URL 단축 서비스 (예: bit.ly)

#### 요구사항
- 긴 URL을 짧은 URL로 변환
- 짧은 URL로 원본 URL 리디렉션
- 월 1억 건의 URL 생성
- 읽기/쓰기 비율 100:1

#### 설계
1. **API 설계**:
   - `POST /api/shorten`: 단축 URL 생성
   - `GET /{shortUrl}`: 원본 URL로 리디렉션

2. **데이터 모델**:
   ```
   url_mappings {
     id: bigint (PK)
     short_url: varchar(7)
     long_url: text
     created_at: timestamp
   }
   ```

3. **URL 생성 전략**:
   - Base62 인코딩 (0-9, a-z, A-Z)
   - 7자리 = 62^7 ≈ 3.5조 개

4. **아키텍처**:
   - 로드 밸런서
   - 애플리케이션 서버 (무상태)
   - Redis 캐시 (읽기 성능)
   - MySQL (마스터-슬레이브 복제)

5. **최적화**:
   - 캐싱: 인기 URL 캐시
   - CDN: 리디렉션 응답 캐시
   - Bloom Filter: 존재하지 않는 URL 빠르게 필터링

---

### 뉴스피드 시스템 (예: Twitter, Facebook)

#### 요구사항
- 게시물 작성 및 조회
- 팔로우한 사용자의 게시물 표시
- 월 활성 사용자 3억 명
- 평균 팔로워 200명

#### 설계
1. **데이터 모델**:
   - users: 사용자 정보
   - posts: 게시물
   - follows: 팔로우 관계
   - newsfeed: 뉴스피드 캐시

2. **뉴스피드 생성 전략**:

   **Fan-out on Write (Push 모델)**
   - 게시물 작성 시 팔로워 피드에 미리 삽입
   - 장점: 읽기 빠름
   - 단점: 쓰기 느림 (팔로워 많을 때)

   **Fan-out on Read (Pull 모델)**
   - 피드 조회 시 팔로우한 사용자의 게시물 조회
   - 장점: 쓰기 빠름
   - 단점: 읽기 느림

   **하이브리드**
   - 일반 사용자: Push
   - 유명인: Pull

3. **아키텍처**:
   - API Gateway
   - Post Service: 게시물 작성
   - Fanout Service: 뉴스피드 생성
   - Newsfeed Service: 피드 조회
   - Kafka: 이벤트 스트리밍
   - Redis: 뉴스피드 캐시
   - Cassandra: 게시물 저장

---

### 채팅 시스템 (예: Slack, WhatsApp)

#### 요구사항
- 1:1 채팅, 그룹 채팅
- 온라인 상태 표시
- 읽음 표시
- 메시지 저장

#### 설계
1. **통신 프로토콜**:
   - WebSocket: 실시간 양방향 통신
   - Long Polling: 대안

2. **아키텍처**:
   - Chat Server: WebSocket 연결 관리
   - Presence Server: 온라인 상태
   - Message Service: 메시지 전송/저장
   - Kafka: 메시지 큐
   - Cassandra: 메시지 저장 (시계열 데이터)
   - Redis: 온라인 상태, 읽음 표시

3. **메시지 전송 흐름**:
   1. 사용자 A가 메시지 전송
   2. Message Service에 저장
   3. Kafka로 이벤트 발행
   4. Chat Server가 수신자에게 전달

---

## 주요 개념 요약

### 비기능적 요구사항
- **확장성 (Scalability)**: 부하 증가 시 대응
- **가용성 (Availability)**: 장애 없이 운영
- **일관성 (Consistency)**: 데이터 정합성
- **성능 (Performance)**: 응답 속도
- **보안 (Security)**: 데이터 보호

### 아키텍처 패턴
- **Monolithic**: 단일 애플리케이션
- **Microservices**: 독립적인 서비스 분리
- **Serverless**: 서버 관리 불필요
- **Event-Driven**: 이벤트 기반 통신

---

## 인터뷰 대비 핵심 질문

1. **수직 확장과 수평 확장의 차이는?**
2. **로드 밸런서의 역할과 알고리즘을 설명하세요.**
3. **데이터베이스 샤딩이란?**
4. **캐시 전략(Cache-Aside, Write-Through)을 설명하세요.**
5. **메시지 큐를 사용하는 이유는?**
6. **CAP 정리를 설명하세요.**
7. **마스터-슬레이브 복제의 장단점은?**
8. **URL 단축 서비스를 어떻게 설계하겠습니까?**

---

## 더 공부할 주제
- [ ] API Gateway
- [ ] Service Mesh
- [ ] Rate Limiting (속도 제한)
- [ ] Consistent Hashing
- [ ] Distributed Tracing
- [ ] Monitoring & Alerting (Prometheus, Grafana)
- [ ] CI/CD Pipeline
