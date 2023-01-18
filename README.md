# DNF_Calculator_N

던파 105제 장비 옵션 계산기

개인 저장용

<br/>  
<br/>  
<br/>

### Comment (배포 대비 설명)

주의사항
- 근위대 세트는 추가하지 않을 예정

장비 옵션 관련
- 어비스 리액터: '속성 부여시 해당 속성 저항 -10' 구현 불가로 사용자가 직접 감안 필요
- 뜨거운 열망의 증표: 적을 보스, 네임드 몬스터로 간주할 시 '아무대상 상변'을 최대치로 조정할 것
- 죽음을 부르는 관: '자신 석화'시 '공격 스택'을 최대치로 조정할 것
- 승리가 약속된 시간: 물리방어율, 마법방어율에 상관없이 HP 50% 미만만 설정하였으므로 실질 방어율은 직접 고려 (주의: 피격 데미지 증가 등의 옵션으로 인한 방어율 감소)
- 컨퓨즈드 코어 슈트: D011-Risa 최대 소환(5개)된 상태로 취급
- 완성형 동력 제어장치: 공격한 속성의 속성 강화, 저항 증가 옵션은 높은 속성 강화, 저항이 증가함
- 미지의 황금비석: 공격 시 모든 상태 이상 내성 증가 옵션을 자동으로 최대치로 적용
- 평화를 위한 투쟁: 자신 출혈 시 출혈 내성 감소 옵션을 자동으로 적용
- 들이치는 희미한 탄식: 쿨타임 12초 이상 스킬 공격력 증가를 35제 이상 모든 스킬에 적용

<br/>  
<br/>

---

### 작업 예정 사항

- 융합픽 구현
- 차원회랑픽 추가

<br/>  
<br/>

---

### 작업일지 (2022.11.30 부터 기록)

2023.01.18

- 귀걸이 작업 완료
- 일부 텍스트 수정
  - 상변 대상 관련
- 신화, 산물 삭제


<br/>

2023.01.17

- 마법석 작업 완료
- 일부 텍스트 수정
  - 공격 스택 관련

<br/>

2023.01.11

- 반지 작업 완료
  - '에너지 서치 링' 수식 최신화
- 보조장비 작업 완료
- 일부 텍스트 수정
  - '검은 별' 수정
  - 레벨링 장비들 옵션 텍스트 수정

<br/>

2022.12.24

- 팔찌 작업 완료
- 일부 텍스트 수정

<br/>

2022.12.23

- '미니어쳐 헤드셋 암릿'까지 작업 완료
  - 장비사전 - 팔찌, 에픽, 105제 이상 설정시 기준

<br/>

2022.12.08

- 작업 프로그램 IntelliJ로 변경

<br/>

2022.12.07

- 목걸이 작업 완료
- '혼돈을 두른 장막', '절망의 발소리' 옵션 조정 텍스트 '잔여 HP 100% - n%'로 변경
- Damage.kt 내 '아토믹 코어 네클레스' 조건부 수정 (실적용 확인 필요)

<br/>

2022.12.06

- 신발 나머지 작업 완료
- '혼돈을 두른 장막' 옵션 조정 텍스트 수정
- Damage.kt 내 '사이버틱 스피드 부츠' 조건부 수정 (실적용 확인 필요)

<br/>

2022.12.05

- '검은 발자국', '하이테크 고기동 강화 부츠', '내딛는 용기', '엑셀러레이터', '드래곤 패스파인더', '움직이는 쇠약한 집착', '대지를 딛는 부츠' 작업 완료
- 하의: 무대의 화려함 옵션 이름을 '무대: 백스탭시전'에서 '백스탭 시전'으로 변경

<br/>

2022.12.04

- '일렉트릭 프루프 부츠' 작업 완료

<br/>

2022.12.03

- '과거를 뒤로 한 전진' 추가

<br/>

2022.12.01

- 브랜치명 형식 날짜로 변경
- 브랜치명 20221201로 신발 작업 중

<br/>

2022.11.30

- 브랜치명 Shoulder로 어깨, 허리 작업 완료

---

<br/>  
<br/>

### 확인해야할 옵션

- "자신 상변" 코드가 끼치는 영향이 있는지
  - 현재로썬 없다고 가정하고 작업 중
- 마부장비 계수 확인

<br/>  
<br/>

외부 라이브러리: json-simple-1.1.1.jar (external_library)

UI code: JAVA

extra : Kotlin
