package telran.range;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import telran.range.exceptions.BallBrokenFloor;


public class BrokenFloorTest {

    private int getMinBrokenFloor(BallBrokenFloor bbf) {
        int low = 0, high = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = (low + high) / 2;
            try {
                bbf.checkFloor(mid);
                low = mid + 1;
            } catch (Exception e) {
                high = mid - 1;
            }
        }
        return low;
    }

    @Test
    void minimalBrokenFloorTest() {
        int [] floors = {200, 17, 1001, 2000};

        for (int i = 0 ; i < floors.length; i++){
            BallBrokenFloor bbf = new BallBrokenFloor(floors[i]);
            assertEquals(bbf.getMinBrokenFloor(), getMinBrokenFloor(bbf));
        }
    }
}

