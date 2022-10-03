# 9_BlueMarble
9월 팀플 부루마블 프로젝트

✈**Console Blue Marble**✈

🎲콘솔로 구현하는 부루마블 게임🎲


<details><summary>게임설명
</summary>

총 20칸으로 이루어져 있으며 한 턴씩 주사위를 던져 나온수만큼 이동이 가능
이동한 땅의 주인이 없을 경우엔 땅 구매가 가능하고, 주인이 있을 경우엔 주인에게 금액 지불
**돈을 얻을 수 있는 방법**으로는 게임판 한바퀴 완주( 10만원 ) , 내가 구입한 땅에 상대방이 도착했을때 , 올림픽 , 황금열쇠 등이 있고
**돈을 잃을 수 있는 방법**으로는 상대방이 구입한 땅에 내가 도착했을 경우 , 황금열쇠 등이 있음.
무인도에 도착하게 되면 주사위가 6이 나오거나 2턴을 쉬어야함
**종료조건**은 플레이어 한명의 돈이 0이 되거나 30턴이 되는 경우가 있음.
</details>



<details><summary>프로젝트 주제 선정 이유
</summary>

**콘솔**로 구현하는 프로젝트는 화면이 단순해 몰입도가 떨어진다고 생각해 최대한 흥미를 끌 수 있는 주제라고 생각되는 게임으로 선택했으며,
콘솔에 출력될때도 글만 보이는것보다 게임판이 구현돼야 흥미롭다고 생각되어 매 판마다 게임판이 출력되고, 말이 이동하도록 구현했음.
아날로그 게임처럼 주사위가 굴러가는 시간을 추가해줬으며, **기존 게임에 있던 황금열쇠 , 무인도 , 올림픽 등 여러가지 기능을 구현했음.**
</details>





<details><summary>📆개발과정 및 일정📆
</summary>

본 프로젝트는 9월 28일부터 10월 5일까지 총 7일간 제작되었음

[9월 28일]<br>
주제 선정 및 역할분담

[9월 29일]<br>
MVC 제작
게임제작을 위한 자료조사
데이터베이스 설계

[9월 30일]<br>
알고리즘 , DTO제작
제작할 게임 규칙 , 예외상황 고려

[9월 29일 ~ 10월 3일]<br>
메소드 구현 및 수정
리드미 , PPT 제작

[10월 4일]<br>
발표

<details><summary>개인일정 박수현
</summary>

| 날짜 | 개발기능 |
| ---------- | ----------------------------------- |
|9/28| 땅 구매 메소드|
|             |도착한 지점의 소유자 확인 메소드|
| |플레이어 자산 확인 메소드|
| |땅 정보 불러오기|
|9/30|통행료 메소드|
| |상대방 땅일 경우 통행료 지불 메소드|
| |통행료 획득 메소드|
|10/1|올림픽 메소드|
| |올림픽 개최 메소드|
| |이미 열린 올림픽 폐막 메소드|
| |땅 매각 메소드|
| |플레이어가 소유한 땅 정보 불러오기 |
</details>





<details><summary>개인일정 손비아
</summary>

| 날짜 | 개발기능 | 
| ------------------- | ---------------------------------------------------------------------|
| 09/29               |플레이어 등록 메소드       					                                   |
|	                    |주사위 (1~6) 메소드         					                                  |
|	                    |말 이동 메소드                				                              	  |
|	                    |전체 플레이어 삭제 메소드 					                                    | 
| 09/30               |플레이어가 소유하고 있는 황금열쇠 사용 메소드		                       |
|                     |황금열쇠 사용가능 여부 확인 로직 구현중			                            |
|                     |깃 머지 오류 수정 및 레포지토리 새로 생성			                          |
|10/01	              |부루마블 판 출력 - 플레이어 위치에 따른 말 위치 구현중	                 |	
|10/02	              |부루마블 판 출력 - 플레이어 위치에 따른 말 위치 구현 완료		            |
|	                    |20만원 당첨						                                                |
|	                    |10만원 차감					                                      	          |	
|                    	|다른 플레이어에게 10만원 받기		                                		   |
|	                    |깃 머지 충돌해결 					                                             |

</details>



<details><summary>개인일정 최유정
</summary>

| 날짜 | 개발기능 |
| ---------- | ----------------------------------- |
|09/29	     |게임제작을 위한 자료조사              |
|	           |데이터베이스 설계                    |	
|09/30	     |부루마블 판 구현중                   |
|10/01	     |부루마블 판 구현중                   |
|	           |무인도 탈출 시도 메소드 구현중       |
|10/02	     |부루마블 판 구현 완료                |
|	           |무인도 탈출 시도 메소드 구현중       |
|10/03	     |무인도 탈출시도 메소드 구현 완료     |
|	           |무인도 탈출 성공 메소드              |
|	           |무인도 탈출 실패 메소드              |
</details>
</details>









핵심 기능
...

사용한 언어 JAVA , SQL<br><br>
<img src="https://img.shields.io/badge/sql-4479A1?style=for-the-badge&logo=MYQL&logoColor=#4479A1">
<img src="https://img.shields.io/badge/eclips-2C22551?style=for-the-badge&logo=EclipsIDE&logoColor=#2C2255">
