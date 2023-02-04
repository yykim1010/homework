package homework;

public class transportation {

    int num;			// 번호
    static int currentGas = 100;		// 주유량
    int currentSpeed = 0;	// 현재 속도
    int acceleration;	// 가속
    int maxPass;		// 최대 승객 수
    String status = "";	// 상태

    // 주유 확인
    static boolean gasLeft() {
        //주유량이 10 이상이어야 운행
        return currentGas >= 10;
    }
}
