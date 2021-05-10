public class EightQuenns {
    int row;
    int column;

    public EightQuenns(int row,int column){
        this.row=row;
        this.column=column;
    }

    public void move(){
        row++;
    }


    public Boolean isAttack(EightQuenns n){
        if(row==n.getRow() || column==n.getColumn()){
            return true;
        }
        else if(Math.abs((n.getColumn()-column))== Math.abs((n.getRow()-row))){
            return true;
        }
        return false;
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

}
