# 운영체제 (Operating Systems)

백엔드 인터뷰를 위한 운영체제 핵심 개념 정리입니다.

## 학습 순서
1. 프로세스와 스레드
2. 메모리 관리
3. CPU 스케줄링
4. 동기화와 데드락
5. 파일 시스템

---

## 1. 프로세스와 스레드

### 프로세스 (Process)
- **정의**: 실행 중인 프로그램의 인스턴스
- **구성 요소**:
  - Code (텍스트): 실행 코드
  - Data: 전역 변수
  - Heap: 동적 할당 메모리
  - Stack: 지역 변수, 함수 호출
- **프로세스 상태**: New → Ready → Running → Waiting → Terminated

### 스레드 (Thread)
- **정의**: 프로세스 내에서 실행되는 작업 단위
- **특징**:
  - 같은 프로세스 내 스레드는 Code, Data, Heap을 공유
  - 각 스레드는 독립적인 Stack을 가짐
- **멀티스레딩 장점**:
  - 응답성 향상
  - 자원 공유
  - 경제성 (프로세스 생성보다 빠름)
  - 멀티코어 활용

### 프로세스 vs 스레드
| 구분 | 프로세스 | 스레드 |
|------|----------|--------|
| 메모리 | 독립적 | 공유 (Code, Data, Heap) |
| 통신 | IPC 필요 | 간단 (공유 메모리) |
| 생성 비용 | 높음 | 낮음 |
| 문맥 교환 | 느림 | 빠름 |

---

## 2. 메모리 관리

### 가상 메모리 (Virtual Memory)
- **목적**: 물리 메모리보다 큰 프로그램 실행 가능
- **페이징 (Paging)**:
  - 논리 메모리를 고정 크기 페이지로 분할
  - 물리 메모리를 프레임으로 분할
  - Page Table로 매핑
- **세그멘테이션 (Segmentation)**:
  - 논리적 단위(Code, Data, Stack)로 분할
  - 가변 크기

### 페이지 교체 알고리즘
- **FIFO (First-In-First-Out)**: 가장 오래된 페이지 교체
- **LRU (Least Recently Used)**: 가장 오래 사용되지 않은 페이지 교체
- **LFU (Least Frequently Used)**: 사용 빈도가 낮은 페이지 교체
- **Optimal**: 미래에 가장 오래 사용되지 않을 페이지 교체 (이론적)

### 스래싱 (Thrashing)
- 페이지 교체가 너무 빈번하게 발생하여 실제 작업보다 교체에 더 많은 시간을 소비하는 현상
- **해결**: Working Set 알고리즘, Page Fault Frequency

---

## 3. CPU 스케줄링

### 스케줄링 알고리즘

#### 비선점형 (Non-preemptive)
- **FCFS (First-Come, First-Served)**: 먼저 온 프로세스 먼저 처리
- **SJF (Shortest Job First)**: 실행 시간이 짧은 프로세스 먼저
- **Priority**: 우선순위가 높은 프로세스 먼저

#### 선점형 (Preemptive)
- **Round Robin**: 타임 슬라이스를 할당하여 순환
- **SRTF (Shortest Remaining Time First)**: 남은 시간이 짧은 프로세스 먼저
- **Multilevel Queue**: 여러 큐를 사용하여 우선순위 관리

### 평가 지표
- **CPU Utilization**: CPU 사용률
- **Throughput**: 단위 시간당 완료된 프로세스 수
- **Turnaround Time**: 프로세스 제출부터 완료까지 시간
- **Waiting Time**: Ready Queue에서 대기한 시간
- **Response Time**: 첫 응답까지 걸린 시간

---

## 4. 동기화와 데드락

### 임계 영역 (Critical Section)
- 공유 자원에 접근하는 코드 영역
- **해결 조건**:
  1. Mutual Exclusion (상호 배제)
  2. Progress (진행)
  3. Bounded Waiting (한정 대기)

### 동기화 도구
- **Mutex (Mutual Exclusion)**: 한 번에 하나의 스레드만 접근
- **Semaphore**: 카운터를 사용한 동기화
  - Binary Semaphore (0 또는 1)
  - Counting Semaphore (0 이상)
- **Monitor**: 고수준 동기화 구조 (Java의 synchronized)

### 데드락 (Deadlock)
- **정의**: 둘 이상의 프로세스가 서로의 자원을 기다리며 무한정 대기

#### 발생 조건 (모두 만족 시)
1. **Mutual Exclusion**: 자원은 한 번에 하나의 프로세스만 사용
2. **Hold and Wait**: 자원을 가진 채로 다른 자원 대기
3. **No Preemption**: 자원을 강제로 빼앗을 수 없음
4. **Circular Wait**: 순환 형태의 대기

#### 처리 방법
- **예방 (Prevention)**: 4가지 조건 중 하나를 제거
- **회피 (Avoidance)**: Banker's Algorithm
- **탐지 및 복구 (Detection & Recovery)**: 주기적으로 탐지하여 복구
- **무시**: 데드락 발생 확률이 낮으면 무시 (UNIX, Windows)

---

## 5. 파일 시스템

### 파일 시스템 구조
- **Boot Block**: 부팅 정보
- **Super Block**: 파일 시스템 메타데이터
- **I-node List**: 파일 메타데이터 (크기, 소유자, 권한, 블록 위치)
- **Data Blocks**: 실제 파일 데이터

### 디렉토리 구조
- **1단계**: 모든 파일이 하나의 디렉토리
- **2단계**: 사용자별 디렉토리
- **트리 구조**: 계층적 디렉토리 (현대 시스템)
- **그래프 구조**: 링크를 통한 공유 (심볼릭 링크, 하드 링크)

### 디스크 스케줄링
- **FCFS**: 요청 순서대로
- **SSTF (Shortest Seek Time First)**: 현재 위치에서 가장 가까운 요청 먼저
- **SCAN (Elevator)**: 한 방향으로 이동하며 처리
- **C-SCAN**: 한 방향으로만 서비스, 끝에서 처음으로 이동

---

## 인터뷰 대비 핵심 질문

1. **프로세스와 스레드의 차이는?**
2. **가상 메모리가 필요한 이유는?**
3. **페이지 교체 알고리즘 중 LRU를 설명하세요.**
4. **데드락의 4가지 발생 조건은?**
5. **뮤텍스와 세마포어의 차이는?**
6. **문맥 교환(Context Switch)이란?**
7. **스래싱을 방지하는 방법은?**
8. **선점형 스케줄링과 비선점형 스케줄링의 차이는?**

---

## 더 공부할 주제
- [ ] 인터럽트와 시스템 콜
- [ ] DMA (Direct Memory Access)
- [ ] RAID 시스템
- [ ] 커널 모드 vs 사용자 모드
- [ ] 가상화와 컨테이너
