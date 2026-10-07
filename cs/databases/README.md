# 데이터베이스 (Databases)

백엔드 인터뷰를 위한 데이터베이스 핵심 개념 정리입니다.

## 학습 순서
1. 관계형 데이터베이스 기초
2. SQL과 쿼리 최적화
3. 인덱스
4. 트랜잭션과 ACID
5. 정규화
6. NoSQL

---

## 1. 관계형 데이터베이스 (RDBMS)

### 기본 개념
- **테이블 (Table)**: 데이터를 행과 열로 구조화
- **행 (Row/Tuple)**: 하나의 레코드
- **열 (Column/Attribute)**: 데이터의 속성
- **기본 키 (Primary Key)**: 행을 고유하게 식별
- **외래 키 (Foreign Key)**: 다른 테이블과의 관계

### 관계 (Relationships)
- **1:1**: 한 행이 다른 테이블의 한 행과 연결
- **1:N**: 한 행이 다른 테이블의 여러 행과 연결
- **N:M**: 여러 행이 서로 여러 행과 연결 (중간 테이블 필요)

---

## 2. SQL과 쿼리 최적화

### SQL 기본 구문

#### SELECT
```sql
SELECT column1, column2
FROM table_name
WHERE condition
GROUP BY column1
HAVING aggregate_condition
ORDER BY column1 DESC
LIMIT 10;
```

#### JOIN
- **INNER JOIN**: 두 테이블의 교집합
- **LEFT JOIN**: 왼쪽 테이블 전체 + 오른쪽 매칭
- **RIGHT JOIN**: 오른쪽 테이블 전체 + 왼쪽 매칭
- **FULL OUTER JOIN**: 두 테이블의 합집합
- **CROSS JOIN**: 카티션 곱

```sql
SELECT users.name, orders.product
FROM users
INNER JOIN orders ON users.id = orders.user_id;
```

#### 집계 함수
- `COUNT()`: 개수
- `SUM()`: 합계
- `AVG()`: 평균
- `MAX()`: 최대값
- `MIN()`: 최소값

### 쿼리 최적화
- **EXPLAIN**: 쿼리 실행 계획 확인
- **인덱스 활용**: WHERE, JOIN, ORDER BY 조건에 인덱스
- **SELECT *  피하기**: 필요한 컬럼만 조회
- **N+1 문제 해결**: JOIN 또는 Batch 조회
- **서브쿼리 최적화**: JOIN으로 변환

---

## 3. 인덱스 (Index)

### 인덱스란?
- 데이터를 빠르게 찾기 위한 자료구조
- B-Tree, Hash, Bitmap 등

### B-Tree 인덱스
- **구조**: 균형 잡힌 트리
- **특징**:
  - 범위 검색 가능
  - 정렬 유지
- **시간 복잡도**: O(log n)

### 인덱스 종류
- **Primary Index**: 기본 키
- **Unique Index**: 중복 불가
- **Composite Index**: 여러 컬럼
- **Covering Index**: 쿼리에 필요한 모든 컬럼 포함

### 인덱스 사용 시 주의점
- **장점**:
  - 조회 속도 향상
  - 정렬, 그룹화 성능 향상
- **단점**:
  - 저장 공간 필요
  - INSERT, UPDATE, DELETE 성능 저하
  - 유지보수 비용

### 인덱스가 사용되지 않는 경우
- 함수를 사용한 컬럼 (`WHERE YEAR(date) = 2024`)
- NOT, !=, <> 연산자
- OR 조건
- 데이터 타입 불일치
- LIKE '%keyword' (앞에 %)

---

## 4. 트랜잭션과 ACID

### 트랜잭션 (Transaction)
- **정의**: 논리적 작업 단위
- **명령어**:
  - `BEGIN`: 시작
  - `COMMIT`: 확정
  - `ROLLBACK`: 취소

### ACID 속성

#### Atomicity (원자성)
- All or Nothing
- 트랜잭션의 모든 연산이 완전히 수행되거나 전혀 수행되지 않음

#### Consistency (일관성)
- 트랜잭션 실행 전후에 데이터베이스가 일관된 상태 유지

#### Isolation (격리성)
- 동시에 실행되는 트랜잭션이 서로 영향을 주지 않음

#### Durability (지속성)
- 커밋된 트랜잭션은 영구적으로 반영

### 격리 수준 (Isolation Level)

| 수준 | Dirty Read | Non-Repeatable Read | Phantom Read |
|------|------------|---------------------|--------------|
| Read Uncommitted | O | O | O |
| Read Committed | X | O | O |
| Repeatable Read | X | X | O |
| Serializable | X | X | X |

- **Dirty Read**: 커밋되지 않은 데이터 읽기
- **Non-Repeatable Read**: 같은 데이터를 다시 읽을 때 값이 변경됨
- **Phantom Read**: 같은 쿼리 재실행 시 행 수가 변경됨

---

## 5. 정규화 (Normalization)

### 목적
- 데이터 중복 최소화
- 이상 현상 방지
- 데이터 무결성 유지

### 정규화 단계

#### 1NF (제1정규형)
- 각 속성이 원자값만 가짐 (더 이상 분해 불가)
- 반복 그룹 제거

#### 2NF (제2정규형)
- 1NF 만족
- 부분 함수 종속 제거 (모든 속성이 기본 키 전체에 종속)

#### 3NF (제3정규형)
- 2NF 만족
- 이행 함수 종속 제거 (A → B → C 제거)

#### BCNF (Boyce-Codd 정규형)
- 3NF 만족
- 모든 결정자가 후보 키

### 역정규화 (Denormalization)
- **목적**: 조회 성능 향상
- **방법**:
  - 테이블 병합
  - 컬럼 중복
  - 집계 테이블 추가

---

## 6. NoSQL

### NoSQL이란?
- Not Only SQL
- 관계형이 아닌 데이터베이스

### 종류

#### Key-Value Store
- **특징**: 키로 값 저장/조회
- **예**: Redis, DynamoDB
- **용도**: 캐싱, 세션 관리

#### Document Store
- **특징**: JSON/XML 문서 저장
- **예**: MongoDB, CouchDB
- **용도**: 유연한 스키마, 계층적 데이터

#### Column-Family Store
- **특징**: 컬럼 단위 저장
- **예**: Cassandra, HBase
- **용도**: 대용량 분산 데이터

#### Graph Database
- **특징**: 노드와 엣지로 관계 표현
- **예**: Neo4j, ArangoDB
- **용도**: 소셜 네트워크, 추천 시스템

### RDBMS vs NoSQL

| 구분 | RDBMS | NoSQL |
|------|-------|-------|
| 스키마 | 고정 | 유연 |
| 확장 | 수직 (Scale-up) | 수평 (Scale-out) |
| 트랜잭션 | ACID | BASE |
| 관계 | JOIN | Denormalized |
| 용도 | 정형 데이터, 복잡한 쿼리 | 비정형 데이터, 대용량 |

### CAP 정리
분산 시스템에서 3가지 중 2가지만 보장 가능:
- **Consistency (일관성)**: 모든 노드가 같은 데이터
- **Availability (가용성)**: 모든 요청이 응답
- **Partition Tolerance (분할 내성)**: 네트워크 장애 시에도 동작

---

## 데이터베이스 성능 최적화

### 파티셔닝 (Partitioning)
- **수평 파티셔닝 (Sharding)**: 행을 나눔
- **수직 파티셔닝**: 열을 나눔

### 레플리케이션 (Replication)
- **Master-Slave**: 읽기 분산
- **Master-Master**: 쓰기 분산

### 캐싱
- **Redis**: In-memory 캐시
- **Memcached**: 분산 캐시
- **캐시 전략**:
  - Cache-Aside (Lazy Loading)
  - Write-Through
  - Write-Behind

### 커넥션 풀 (Connection Pool)
- 데이터베이스 연결 재사용
- 연결 생성 비용 절감

---

## 인터뷰 대비 핵심 질문

1. **인덱스란 무엇이고 언제 사용하나요?**
2. **트랜잭션의 ACID 속성을 설명하세요.**
3. **정규화가 필요한 이유는?**
4. **INNER JOIN과 OUTER JOIN의 차이는?**
5. **인덱스 사용 시 주의할 점은?**
6. **N+1 문제란 무엇이고 어떻게 해결하나요?**
7. **격리 수준(Isolation Level)을 설명하세요.**
8. **RDBMS와 NoSQL의 차이는?**
9. **샤딩(Sharding)이란 무엇인가요?**
10. **클러스터 인덱스와 논클러스터 인덱스의 차이는?**

---

## 더 공부할 주제
- [ ] ORM (JPA/Hibernate)
- [ ] 데이터베이스 락 (Lock)
- [ ] 옵티미스틱 락 vs 페시미스틱 락
- [ ] MVCC (Multi-Version Concurrency Control)
- [ ] 쿼리 실행 계획 분석
- [ ] DB 마이그레이션
