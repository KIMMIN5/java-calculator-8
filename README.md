# java-calculator-precourse

---

## USE CASE

1. 사용자가 문자열을 입력한다.
2. 문자열 내 양의 정수들의 합을 얻는다.

---

### 내부 로직

1. 입력 문자열(inputString)을 구분자(기본, 커스텀)를 기준으로 분할(separateInput)
2. 분할 문자열(separateInput)에서 양수만을 추출(extractNumber)
3. 정수(extractNumber)들의 합(sum)을 계산 후 반환

---

### 내부 로직 구현 방법

1. 분할(separateInput) \
   [참고] 기본 구분자(Basic Delimiter): ',', ':' \
   [참고] 커스텀 구분자(Custom Delimiter): 입력값(inputString) 초반의 "//" ~ "\n" 사이의 문자 \
   1-1. 기본 구분자, 커스텀 구분자 정의
    - 기본 구분자, 커스텀 구분자가 있는지 확인하기 위해 정규표현식을 이용
    - 기본 구분자: (",|:"), 커스텀 구분자: ("//(.)\n(.*))
    - **Java.util.regex Pattern, Matcher**를 사용해서 구분자를 찾기

   1-2. 구분자를 기준으로 입력 문자열 분할
    - 커스텀 구분자가 있으면 커스텀 구분자 정의 및 커스텀 구분자, 기본 구분자로 분할
    - 커스텀 구분자가 없으면 기본 구분자로 분할

   1-3. 분할된 문자열 반환


2. 추출(extractNumbers) \
   2-1. 분할 문자열을 탐색하며 양수 찾기 \
   2-2. 찾은 양수를 반환


3. 합 계산(summateNumbers) \
   3-1. 양수를 더하기 \
   3-2. 계산이 끝난 합을 반환

---

### 내부로직 예외처리

1. 분할(separateInput) \
   1-1. 구분자(기본, 커스텀)가 없을 시(No Delimiter)
    - 2번 과정(추출) 진행


2. 추출(extractNumbers) \
   2-1. 추출 할 숫자가 없을 시(빈문자열)
    - 0으로 처리

   2-2. 양수가 아닐 시(음수, 실수)
    - IllegalArgumentException 발생 후, 종료
   
   2-3. 숫자가 없을 시(문자, 특수문자 등)
    - IllegalArgumentException 발생 후, 종료


3. 합 계산(summateNumbers) \
   3-1. int 자료형보다 큰 값이 들어올 시, 오버플로우 발생
    - 문제에서 자료형에 관한 설명이 없으므로 해당 예외는 무시