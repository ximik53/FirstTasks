package Subs;
import java.util.*;

public class Subsequence {
    public <sub> boolean find(List <sub> first, List <sub> second) {
        if (first.isEmpty() || second.isEmpty());
        boolean verification = true;
        int point = 0;
        for (int firstIter = 0; firstIter < first.size(); firstIter++){
            if (verification != true) break;
            verification = false;
            for (int secondIter = point; secondIter < second.size(); secondIter++){
                if (first.get(firstIter).equals(second.get(secondIter))){
                    verification = true;
                    point = secondIter + 1;
                    break;
                }
            }
        }
        return verification;
    }
}

