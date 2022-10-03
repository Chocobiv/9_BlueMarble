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





<details><summary>📆전체 개발과정 및 일정📆
</summary>

본 프로젝트는 9월 28일부터 10월 5일까지 총 7일간 제작되었음

[9월 28일]
주제 선정 및 역할분담

[9월 29일]
MVC 제작
게임제작을 위한 자료조사
데이터베이스 설계

[9월 30일]
알고리즘 , DTO제작
제작할 게임 규칙 , 예외상황 고려

[9월 29일 ~ 10월 3일]
메소드 구현 및 수정
리드미 , PPT 제작

[10월 4일]
발표
</details>



개인일정 손비아


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

<br>개인일정 최유정<br>
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

핵심 기능
...

사용한 언어 JAVA , SQL
<svg role="img" viewBox="0 0 20 20" xmlns="http://www.w3.org/20/svg"><title>MySQL</title><path d="M16.405 5.501c-.115 0-.193.014-.274.033v.013h.014c.054.104.146.18.214.273.054.107.1.214.154.32l.014-.015c.094-.066.14-.172.14-.333-.04-.047-.046-.094-.08-.14-.04-.067-.126-.1-.18-.153zM5.77 18.695h-.927a50.854 50.854 0 00-.27-4.41h-.008l-1.41 4.41H2.45l-1.4-4.41h-.01a72.892 72.892 0 00-.195 4.41H0c.055-1.966.192-3.81.41-5.53h1.15l1.335 4.064h.008l1.347-4.064h1.095c.242 2.015.384 3.86.428 5.53zm4.017-4.08c-.378 2.045-.876 3.533-1.492 4.46-.482.716-1.01 1.073-1.583 1.073-.153 0-.34-.046-.566-.138v-.494c.11.017.24.026.386.026.268 0 .483-.075.647-.222.197-.18.295-.382.295-.605 0-.155-.077-.47-.23-.944L6.23 14.615h.91l.727 2.36c.164.536.233.91.205 1.123.4-1.064.678-2.227.835-3.483zm12.325 4.08h-2.63v-5.53h.885v4.85h1.745zm-3.32.135l-1.016-.5c.09-.076.177-.158.255-.25.433-.506.648-1.258.648-2.253 0-1.83-.718-2.746-2.155-2.746-.704 0-1.254.232-1.65.697-.43.508-.646 1.256-.646 2.245 0 .972.19 1.686.574 2.14.35.41.877.615 1.583.615.264 0 .506-.033.725-.098l1.325.772.36-.622zM15.5 17.588c-.225-.36-.337-.94-.337-1.736 0-1.393.424-2.09 1.27-2.09.443 0 .77.167.977.5.224.362.336.936.336 1.723 0 1.404-.424 2.108-1.27 2.108-.445 0-.77-.167-.978-.5zm-1.658-.425c0 .47-.172.856-.516 1.156-.344.3-.803.45-1.384.45-.543 0-1.064-.172-1.573-.515l.237-.476c.438.22.833.328 1.19.328.332 0 .593-.073.783-.22a.754.754 0 00.3-.615c0-.33-.23-.61-.648-.845-.388-.213-1.163-.657-1.163-.657-.422-.307-.632-.636-.632-1.177 0-.45.157-.81.47-1.085.315-.278.72-.415 1.22-.415.512 0 .98.136 1.4.41l-.213.476a2.726 2.726 0 00-1.064-.23c-.283 0-.502.068-.654.206a.685.685 0 00-.248.524c0 .328.234.61.666.85.393.215 1.187.67 1.187.67.433.305.648.63.648 1.168zm9.382-5.852c-.535-.014-.95.04-1.297.188-.1.04-.26.04-.274.167.055.053.063.14.11.214.08.134.218.313.346.407.14.11.28.216.427.31.26.16.555.255.81.416.145.094.293.213.44.313.073.05.12.14.214.172v-.02c-.046-.06-.06-.147-.105-.214-.067-.067-.134-.127-.2-.193a3.223 3.223 0 00-.695-.675c-.214-.146-.682-.35-.77-.595l-.013-.014c.146-.013.32-.066.46-.106.227-.06.435-.047.67-.106.106-.027.213-.06.32-.094v-.06c-.12-.12-.21-.283-.334-.395a8.867 8.867 0 00-1.104-.823c-.21-.134-.476-.22-.697-.334-.08-.04-.214-.06-.26-.127-.12-.146-.19-.34-.275-.514a17.69 17.69 0 01-.547-1.163c-.12-.262-.193-.523-.34-.763-.69-1.137-1.437-1.826-2.586-2.5-.247-.14-.543-.2-.856-.274-.167-.008-.334-.02-.5-.027-.11-.047-.216-.174-.31-.235-.38-.24-1.364-.76-1.644-.072-.18.434.267.862.422 1.082.115.153.26.328.34.5.047.116.06.235.107.356.106.294.207.622.347.897.073.14.153.287.247.413.054.073.146.107.167.227-.094.136-.1.334-.154.5-.24.757-.146 1.693.194 2.25.107.166.362.534.703.393.3-.12.234-.5.32-.835.02-.08.007-.133.048-.187v.015c.094.188.188.367.274.555.206.328.566.668.867.895.16.12.287.328.487.402v-.02h-.015c-.043-.058-.1-.086-.154-.133a3.445 3.445 0 01-.35-.4 8.76 8.76 0 01-.747-1.218c-.11-.21-.202-.436-.29-.643-.04-.08-.04-.2-.107-.24-.1.146-.247.273-.32.453-.127.288-.14.642-.188 1.01-.027.007-.014 0-.027.014-.214-.052-.287-.274-.367-.46-.2-.475-.233-1.238-.06-1.785.047-.14.247-.582.167-.716-.042-.127-.174-.2-.247-.303a2.478 2.478 0 01-.24-.427c-.16-.374-.24-.788-.414-1.162-.08-.173-.22-.354-.334-.513-.127-.18-.267-.307-.368-.52-.033-.073-.08-.194-.027-.274.014-.054.042-.075.094-.09.088-.072.335.022.422.062.247.1.455.194.662.334.094.066.195.193.315.226h.14c.214.047.455.014.655.073.355.114.675.28.962.46a5.953 5.953 0 012.085 2.286c.08.154.115.295.188.455.14.33.313.663.455.982.14.315.275.636.476.897.1.14.502.213.682.286.133.06.34.115.46.188.23.14.454.3.67.454.11.076.443.243.463.378z"/></svg>
<svg role="img" viewBox="0 0 200 200" xmlns="http://www.w3.org/2000/svg"><title>Eclipse IDE</title><path d="M11.109.024a15.58 15.58 0 00-.737.023C6.728.361 3.469 2.517 1.579 5.86A12.53 12.53 0 00.021 11.11c-.04.517-.02 1.745.035 2.208.306 2.682 1.353 5.06 3.07 6.965 1.962 2.173 4.586 3.467 7.437 3.663.42.032 1.043.04 1.02.012a2.404 2.404 0 00-.338-.074c-1.674-.33-3.388-1.13-4.777-2.232a12.344 12.344 0 01-2.45-2.636A12.387 12.387 0 011.884 12.5a12.413 12.413 0 01.56-4.274c.785-2.522 2.37-4.726 4.475-6.228A11.073 11.073 0 0111.156.122l.443-.098zm1.474.51C10.646.65 8.807 1.299 7.301 2.4 5.426 3.77 3.995 5.644 3.22 7.746c-.145.397-.282.82-.282.879 0 .012 3.828.024 10.31.024 8.463 0 10.315-.008 10.315-.036 0-.047-.153-.525-.283-.878-.153-.42-.576-1.31-.82-1.722-.4-.683-.91-1.373-1.474-1.992-1.65-1.82-3.593-2.934-5.82-3.334-.785-.141-1.8-.2-2.585-.153zM23.83 9.97c-.02 0-4.792 0-10.609.004l-10.573.008-.011.059c-.036.16-.134 1.081-.134 1.242 0 .028 1.785.032 10.746.032H24v-.075c0-.102-.07-.791-.106-1.054-.02-.16-.04-.216-.063-.216zm-10.573 2.635c-9.37-.004-10.73 0-10.742.035-.02.04.024.557.075.973.02.157.035.298.035.314 0 .027 2.137.035 10.624.035h10.624l.024-.188c.043-.326.102-.97.094-1.067l-.008-.094zm.003 2.718c-8.882 0-10.321.004-10.321.035 0 .02.054.208.12.42a11.122 11.122 0 002.072 3.741c.282.342.945 1.036 1.228 1.287 1.568 1.4 3.247 2.216 5.18 2.53.605.094.886.113 1.75.11.91 0 1.297-.032 2.023-.177 2.11-.416 3.914-1.451 5.53-3.17 1.267-1.348 2.106-2.76 2.628-4.41l.117-.366z"/></svg>
