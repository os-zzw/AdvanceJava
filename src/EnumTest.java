/**
 * Created by john on 2016/11/20.
 */
public class EnumTest {

    public static void main(String[] args) {
        WeekDay sun = WeekDay.SUN;
        System.out.println(sun);
        System.out.println(sun.ordinal());
        System.out.println(WeekDay.values().length);

    }

    public enum WeekDay {
        SUN(1), MON, TUE, WED, THI, FRI, SAT;

        WeekDay(int day) {
        }

        WeekDay() {
        }
    }

    public enum TrafficLamp {
        RED(30) {
            @Override
            public TrafficLamp nextLamp() {
                return GREEN;
            }
        }, GREEN(50) {
            @Override
            public TrafficLamp nextLamp() {
                return YELLOW;
            }
        }, YELLOW(5) {
            @Override
            public TrafficLamp nextLamp() {
                return RED;
            }
        };
        public abstract TrafficLamp nextLamp();

        private int time;

        TrafficLamp(int time) {
            this.time=time;
        }
    }
}
