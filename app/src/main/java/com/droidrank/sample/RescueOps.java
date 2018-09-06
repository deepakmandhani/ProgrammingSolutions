import java.util.Scanner;

class RescuseOps {

    static String solve(String input, int length) {
        StringBuilder result = new StringBuilder();
        int toConsider = 0;
        int x = 0, y = 0, diagonal = 0, remaining = 0;
        for (int i = 0; i < length; i++) {
            if (input.charAt(i) == 'M') {
                if (toConsider == 0) {
                    y++;
                } else if (toConsider == 1) {
                    x++;
                } else if (toConsider == 2) {
                    y--;
                } else {
                    x--;
                }
            } else {
                if (input.charAt(i) == 'L') {
                    if (toConsider == 0) {
                        toConsider = 3;
                    } else if (toConsider == 1) {
                        toConsider = 0;
                    } else if (toConsider == 2) {
                        toConsider = 1;
                    } else {
                        toConsider = 2;
                    }
                } else {
                    if (toConsider == 0) {
                        toConsider = 1;
                    } else if (toConsider == 1) {
                        toConsider = 2;
                    } else if (toConsider == 2) {
                        toConsider = 3;
                    } else {
                        toConsider = 0;
                    }
                }
            }
        }

        diagonal = Math.min(Math.abs(x), Math.abs(y));
        if (Math.abs(x) > Math.abs(y)) {
            if (x >0) {
                if (y >=0) {
                    for (int i = 0; i < diagonal; i++) {
                        result.append('1');
                    }
                    remaining = Math.abs(Math.abs(x) - diagonal);
                    for (int i = 0; i < remaining; i++) {
                        result.append('6');
                    }
                } else {
                    for (int i = 0; i < diagonal; i++) {
                        result.append('2');
                    }
                    remaining = Math.abs(Math.abs(x) - diagonal);
                    for (int i = 0; i < remaining; i++) {
                        result.append('6');
                    }
                }
            } else {
                if (y >= 0) {
                    for (int i = 0; i < diagonal; i++) {
                        result.append('4');
                    }
                    remaining = Math.abs(Math.abs(x) - diagonal);
                    for (int i = 0; i < remaining; i++) {
                        result.append('8');
                    }
                } else {
                    for (int i = 0; i < diagonal; i++) {
                        result.append('3');
                    }
                    remaining = Math.abs(Math.abs(x) - diagonal);
                    for (int i = 0; i < remaining; i++) {
                        result.append('8');
                    }
                }

            }
        } else {
            if (y > 0) {
                if (x >=0) {
                    for (int i = 0; i < diagonal; i++) {
                        result.append('1');
                    }
                    remaining = Math.abs(Math.abs(y) - diagonal);
                    for (int i = 0; i < remaining; i++) {
                        result.append('5');
                    }

                } else {
                    for (int i = 0; i < diagonal; i++) {
                        result.append('4');
                    }
                    remaining = Math.abs(Math.abs(y) - diagonal);
                    for (int i = 0; i < remaining; i++) {
                        result.append('5');
                    }
                }
            } else {
                if (x >=0) {
                    for (int i = 0; i < diagonal; i++) {
                        result.append('2');
                    }
                    remaining = Math.abs(Math.abs(y) - diagonal);
                    for (int i = 0; i < remaining; i++) {
                        result.append('7');
                    }
                } else {
                    for (int i = 0; i < diagonal; i++) {
                        result.append('3');
                    }
                    remaining = Math.abs(Math.abs(y) - diagonal);
                    for (int i = 0; i < remaining; i++) {
                        result.append('7');
                    }
                }
            }
        }

        return result + "0";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt(), siz;
        String input;
        while (testCase > 0) {
            siz = sc.nextInt();
            input = sc.next();
            System.out.println(solve(input, siz));
            testCase--;
        }
    }
}
