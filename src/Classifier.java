import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Classifier {
    private List<Input> train, test;
    private float [] classProbabilities;
    private float [] conditionalProbabilitiesTrue;
    private float [] conditionalProbabiltiesFalse;


    private Classifier(String s1, String s2){
        test = readData(s2);
        train = readData(s1);

    }

    private List<Input> readData(String fname){
        List<Input> l = new ArrayList<>();
        try {
            Scanner s = new Scanner(new File(fname));
            while(s.hasNextLine()){
                l.add(new Input(s.nextLine()));
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return l;
    }

    void calculateClassProbabilities(){
        classProbabilities = new float[train.get(0).getSize()];

        for(int i = 0; i < train.size(); i++){
            for(int x = 0; x < train.get(0).getSize(); x++){
                int[] check = train.get(i).getValues();
                if(check[x] == 1){
                    classProbabilities[i] = classProbabilities[i] + 1;
                }
            }
        }

        for(int y = 0; y <train.size(); y++){
            classProbabilities[y] = classProbabilities[y] / train.size();
        }
    }

    void calculateConditionalProbabilities(){
        conditionalProbabilitiesTrue = new float[train.get(0).getSize()];
        conditionalProbabiltiesFalse = new float[train.get(0).getSize()];
        int trueCounter = 0;
        int falseCounter = 0;

        for(int i = 0; i < train.size(); i++){
            for(int n = 0; n < train.get(0).getSize() - 1; n++){
                int[] check = train.get(i).getValues();
                if(check[n] == 1 && check[check.length - 1] == 1){
                    conditionalProbabilitiesTrue[n] = conditionalProbabilitiesTrue[n] + 1;
                    trueCounter++;
                }
                if(check[n] == 1 && check[check.length - 1] == 0){
                    conditionalProbabiltiesFalse[n] = conditionalProbabiltiesFalse[n] + 1;
                    falseCounter++;
                }
            }
        }

        for(int y = 0; y < train.size(); y++){
            if(conditionalProbabilitiesTrue[y] != 0)conditionalProbabilitiesTrue[y] = conditionalProbabilitiesTrue[y] / trueCounter;
            if(conditionalProbabiltiesFalse[y] != 0)conditionalProbabiltiesFalse[y] = conditionalProbabiltiesFalse[y] / falseCounter;
        }
    }

public void main(String args[]){
        new Classifier(args[0], args[1]);
}
}
