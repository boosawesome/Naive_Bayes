import java.util.StringTokenizer;

class Input {
    private int[] values = new int[12];

    Input(String line){
        StringTokenizer token = new StringTokenizer(line);
        for(int i = 0; i < 13; i++){
            values[i] = Integer.parseInt(token.nextToken());
        }
    }

    int[] getValues(){
        return values;
    }

    int getSpam(){
        return values[values.length - 1];
    }

    int getSize(){
        return values.length;
    }
}
