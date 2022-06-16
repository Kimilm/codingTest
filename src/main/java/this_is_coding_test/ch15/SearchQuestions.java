package this_is_coding_test.ch15;

import java.util.*;
import java.util.stream.Collectors;

public class SearchQuestions {
    /**
     * 난이도: 중
     * 1 <= N <= 1_000_000
     * -10^9 <= x <= 10^9
     * 제한) 시간: 1초, 메모리: 128MB
     */
    public int 정렬된_배열에서_특정_수의_개수_구하기(String[] input) {
        String[] nx = input[0].split(" ");
        int n = Integer.parseInt(nx[0]);
        int x = Integer.parseInt(nx[1]);
        int[] array = Arrays.stream(input[1].split(" ")).mapToInt(Integer::parseInt).toArray();

        int pre = binSearch(array, 0, n - 1, x, true);
        int post = binSearch(array, 0, n - 1, x, false);

        return post - pre - 1;
    }

    public int binSearch(int[] array, int start, int end, int x, boolean flag) {
        // 못 찾았다면
        if (start == end) {
            return -1;
        }

        int mid = (start + end) / 2;

        // 중간값의 좌우에서 경계를 찾았다면
        if (array[mid] == x) {
            // flag == true 라면 좌측 경계 탐색
            if (flag && array[mid - 1] != x) {
                return mid - 1;
            }
            // flag == false 라면 우측 경계 탐색
            if (!flag && array[mid + 1] != x) {
                return mid + 1;
            }
        } else {
            if (flag && array[mid + 1] == x) {
                return mid;
            }
            if (!flag && array[mid - 1] == x) {
                return mid;
            }
        }
        // 중간값이 찾으려는 값이라면
        if (array[mid] == x) {
            // flag == true 라면 좌측 탐색
            if (flag) {
                return binSearch(array, start, mid, x, flag);
            }
            // flag == false 라면 우측 탐색
            else {
                return binSearch(array, mid, end, x, flag);
            }
        }
        // 중간값이 찾으려는 값보다 크다면 좌측 탐색
        else if (array[mid] > x) {
            return binSearch(array, start, mid - 1, x, flag);
        }
        // 중간값이 찾으려는 값보다 작다면 우측 탐색
        else {
            return binSearch(array, mid + 1, end, x, flag);
        }
    }

    /**
     * logN의 알고리즘을 설계해야 함
     * 범위를 반씩 줄이며 찾는 수의 경계에 존재하는 값을 찾으려고 해봤음
     * 문제에서 주어진 케이스는 통과했지만 모두 같은 수로 이루어진 순열, 길이가 1인 순열, 시작과 끝 인덱스에 존재하는 수는 탐색할 수 없음
     * <p>
     * 해설지도 이런식의 코드를 작성함
     * 다만 나는 찾으려는 x 값 범위의 시작 직전과 끝난 직후 인덱스를 찾는 코드를 작성했고
     * 2를 찾음, 1 1 2 2 2 2 3 이라면 1과 6
     * 해설지에서는 x값 범위의 시작과 끝 인덱스를 구함
     * 2를 찾음, 1 1 2 2 2 2 3 이라면 2와 5
     * <p>
     * 또한 시작과 끝을 찾는 2개의 메서드를 선언하였음
     * 하나의 메서드에서 둘 다 찾으려고 하니 메서드 내부에서 + - 연산에 의해 arrayindexoutofboundsexception이 발생했음
     * <p>
     * 몾 찾는 경우를 start == end 로 설정하지 않고 start > end 로 설정하였음
     * <p>
     * 풀이과정은 비슷했음. 좀 더 정교해야했음
     */
    public int 정렬된_배열에서_특정_수의_개수_구하기_2(String[] input) {
        String[] nx = input[0].split(" ");
        int n = Integer.parseInt(nx[0]);
        int x = Integer.parseInt(nx[1]);
        int[] array = Arrays.stream(input[1].split(" ")).mapToInt(Integer::parseInt).toArray();

        int first = findFirst(array, x, 0, n - 1);

        // 못 찾았다면 값이 x인 원소가 존재하지 않음
        if (first == -1) {
            return -1;
        }

        int last = findLast(array, x, 0, n - 1);

        return last - first + 1;
    }

    // 시작 위치 이진 탐색
    public int findFirst(int[] array, int target, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        // 해당 값을 가지는 원소 중에서 가장 왼쪽에 있는 경우에만 인덱스 반환
        if ((mid == 0 || target > array[mid - 1]) && array[mid] == target) {
            return mid;
        }
        // 중간 지점의 값보다 찾고자 하는 값이 작거나 같은 경우 왼쪽 확인
        else if (array[mid] >= target) {
            return findFirst(array, target, start, mid - 1);
        }
        // 중간 지점의 값보다 찾고자 하는 값이 큰 경우 오른쪽 확인
        else {
            return findFirst(array, target, mid + 1, end);
        }
    }

    // 끝 위치 이진 탐색
    public int findLast(int[] array, int target, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        // 해당 값을 가지는 원소 중에서 가장 오른쪽에 있는 경우에만 인덱스 반환
        if ((mid == array.length - 1 || target < array[mid + 1]) && array[mid] == target) {
            return mid;
        }
        // 중간 지점의 값보다 찾고자 하는 값이 작은 경우 왼쪽 확인
        else if (array[mid] > target) {
            return findLast(array, target, start, mid - 1);
        }
        // 중간 지점의 값보다 찾고자 하는 값이 크거나 같은 경우 오른쪽 확인
        else {
            return findLast(array, target, mid + 1, end);
        }
    }

    /**
     * 난이도: 중하
     * 1 <= N <= 1_000_000
     * -10^9 <= x <= 10^9
     * 제한) 시간: 1초, 메모리: 128MB
     */
    public int 고정점(String[] input) {
        int n = Integer.parseInt(input[0]);
        int[] array = Arrays.stream(input[1].split(" ")).mapToInt(Integer::parseInt).toArray();

        return binSearch(array, 0, n - 1);
    }

    public int binSearch(int[] array, int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;

        if (array[mid] == mid) {
            return mid;
        }

        if (array[mid] > mid) {
            return binSearch(array, start, mid - 1);
        } else {
            return binSearch(array, mid + 1, end);
        }
    }

    /**
     * O(logN) 의 알고리즘을 설계하지 못하면 시간초과
     */

    /**
     * 난이도: 중
     * 2 <= N <= 200_000
     * 2 <= C <= N
     * 1 <= x <= 1_000_000_000
     * 제한) 시간: 2초, 메모리: 128MB
     * https://www.acmicpc.net/problem/2110
     */
    public int 공유기_설치(String[] input) {
        String[] nc = input[0].split(" ");
        int n = Integer.parseInt(nc[0]);
        int c = Integer.parseInt(nc[1]) - 1;

        int[] house = Arrays.stream(input[1].split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        int start = 0;
        int end = n - 1;
        int answer = house[end] - house[start];

        for (int i = c; i > 1; i--) {
            int next = binSearch(house, (house[start] + house[end]) / i, start, end);

            int left = house[next] - house[start];
            int right = house[end] - house[next];
            answer = Integer.min(left, right);

            // 다음으로 나누려면 원소의 개수가 많아야 함
            if (next - start < end - next) {
                start = next;
            } else {
                end = next;
            }
        }

        return answer;
    }

    public int binSearch(int[] array, int target, int start, int end) {
        if (end - start == 1) {
            return Math.abs(target - array[start]) < Math.abs(target - array[end]) ? start : end;
        }

        int mid = (start + end) / 2;

        if (array[mid] == target) {
            return mid;
        }
        if (array[mid] < target) {
            return binSearch(array, target, mid, end);
        } else {
            return binSearch(array, target, start, mid);
        }
    }

    /**
     * '가장 인접한 두 공유기 사이의 거리' 의 최댓값을 탐색해야 하는 문제
     * 각 집의 좌표가 최대 10억, 이진 탐색 사용
     * 이진 탐색으로 가장 인접한 두 공유기 사이의 거리를 조절해가며
     * 매 순간 실제로 공유기를 설치하여 c보다 많은 개수로 공유기를 설치할 수 있는지 체크
     * <p>
     * C보다 많은 갯수의 공유기를 설치할 수 있다면 가장 인접한 두 공유기 사이의 거리 값을 증가시켜서
     * 더 큰 값에 대해서도 성립하는지를 체크하기 위해 다시 탐색을 수행
     * 7장에서 다룬 '떡볶이 떡 만들기' 문제와 유사하게 이진 탐색을 이용해 해결할 수 있는 파라메트릭 서치 유형의 문제
     * <p>
     * [1, 2, 4, 8, 9] 수열에서 C가 3일때
     * 공유기 사이의 거리 gap 은 최소 1, 최대 8
     * 공유기를 앞에서부터 순서대로 설치한다고 할때
     * 절반 크기인 4 gap 으로 공유기를 설치하면
     * 1 2 4 8 9
     * O X X O X
     * 의 형태로 2개의 공유기가 설치됨 < C, 범위를 최소 1, 최대 3으로 수정
     * 1 - 3 범위의 gap 중 절반인 2로 공유기를 설치하면
     * 1 2 4 8 9
     * O X O O X
     * 의 형태로 3개의 공유기가 설치됨 == C, 현재의 gap 을 저장하고 더 큰 범위 탐색, 범위 최소 3, 최대 3으로 수정
     * 1 2 4 8 9
     * O X O O X
     * 의 형태로 3개의 공유기가 설치됨 == C, 범위가 최소 3, 최대 3이므로 더이상 gap 을 증가시킬 수 없음
     * 최적의 값은 3
     * <p>
     * 거리 에 대해서 이진탐색을 적용해야 함
     * 배열의 원소 갯수는 20만개
     * 원소의 최댓값은 10억, 탐색 범위가 10억 -> 이걸 줄이기 위해 이진탐색을 사용
     * 조건을 주의깊게 읽자
     */
    public int 공유기_설치_2(String[] input) {
        String[] nc = input[0].split(" ");
        int n = Integer.parseInt(nc[0]);
        int c = Integer.parseInt(nc[1]);

        int[] house = Arrays.stream(input[1].split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        // 가능한 최소 거리
        int start = 1;
        // 가능한 최대 거리
        int end = house[n - 1] - house[0];
        int result = 0;

        while (start <= end) {
            int mid = (start + end) / 2;
            int value = house[0];
            int count = 1;
            // 현재의 mid 값을 이용해 공유기 설치
            for (int i = 1; i < n; i++) {
                // 앞에서부터 하나씩 설치
                if (house[i] >= value + mid) {
                    value = house[i];
                    ++count;
                }
            }
            // C개 이상의 공유기를 설치할 수 있다면
            if (count >= c) {
                // 설치 가능 최소 거리 증가 조정
                start = mid + 1;
                // 현재 상태에서 최적의 결과 저장
                result = mid;
            }
            // 없다면
            else {
                // 설치 가능 최대 거리 감소 조정
                end = mid - 1;
            }
        }

        return result;
    }

    public Integer[] treeSet(Integer[] nonSortedArray) {
        TreeSet<Integer> tree = Arrays.stream(nonSortedArray).collect(Collectors.toCollection(TreeSet::new));
        return tree.toArray(Integer[]::new);
    }

    /**
     * 난이도: 상
     * [가사 단어]
     * 2 <= words.length <= 100_000 (각 단어의 길이)
     * 2 <= 전체 가사 단어 길이의 합 <= 1_000_000
     * 알파벳 소문자로만 구성, 중복x
     * [검색 키워드]
     * 2 <= queries <= 100_000
     * 2 <= 전체 검색 키워드 길이의 합 <= 1_000_000
     * 알파벳 소문자 + 와일드카드(?)로 구성, 중복o
     * 제한) 시간: 1초, 메모리: 128MB
     * https://programmers.co.kr/learn/courses/30/lessons/60060
     */
    public int[] 가사_검색(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        QueryString[] queryStrings = Arrays.stream(queries)
                .map(QueryString::new)
                .toArray(QueryString[]::new);

        // 십만 * 십만 == 백억
        for (int i = 0; i < queries.length; i++) {
            int count = (int) Arrays.stream(words).filter(queryStrings[i]::match).count();
            answer[i] = count;
        }

        return answer;
    }

    static class QueryString {
        int flag;
        int length;
        int wildCardStartIdx;
        int wildCardEndIdx;
        String prefix;
        String postfix;

        public QueryString(String string) {
            this.length = string.length();
            this.wildCardStartIdx = string.indexOf("?");
            this.wildCardEndIdx = string.lastIndexOf("?");
            // 와일드카드가 접두사
            if (wildCardStartIdx == 0) {
                prefix = string.substring(0, wildCardEndIdx + 1);
                postfix = string.substring(wildCardEndIdx + 1);
                flag = 0;
            }
            // 와일드카드가 접미사
            else {
                prefix = string.substring(0, wildCardStartIdx);
                postfix = string.substring(wildCardStartIdx);
                flag = 1;
            }
        }

        public boolean match(String string) {
            boolean result = this.length == string.length();
            if (result) {
                // 와일드카드가 접두사
                if (flag == 0) {
                    result = string.endsWith(postfix);
                }
                // 와일드카드가 접미사
                else if (flag == 1) {
                    result = string.startsWith(prefix);
                }
            }
            return result;
        }

        public int compareTo(String string) {
            // 와일드카드가 접두사
            if (wildCardStartIdx == 0) {
                int i = postfix.compareTo(string.substring(wildCardEndIdx + 1));
                return i;
            }
            // 와일드카드가 접미사
            else {
                int i = prefix.compareTo(string.substring(0, wildCardStartIdx));
                return i;
            }
        }
    }

    /**
     * 이진 탐색 문제에서 이진 탐색을 사용하지 않음 -> 시간초과
     */
}
