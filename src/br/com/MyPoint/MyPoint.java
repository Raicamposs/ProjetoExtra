package br.com.MyPoint;

public class MyPoint {

    /**
     * Método construtor. Você deve utiliza-lo para criar o registro de um novo
     * MyPoint.
     *
     * @param x valor do ponto x
     * @param y valor do ponto y
     * @author Raiane Campos
     */
    
    
    private int x = 0, y = 0;

    // Declaração do construtor da classe MyPoint
    public MyPoint() {
    }

    // Declaração de outro construtor da classe MyPoint
    public MyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Declaração do método getX
    public int getX() {
        return x;
        /* Retorna o valor de x
         * @return Int - valor de x*/
    }

    //Declaração do método setX
    public void setX(int x) {
        this.x = x;
        // Altera x para o valor passado
    }

    //Declaração do método getY
    public int getY() {
        return y;
        /* Retorna o valor de y
         * @return Int - valor de y*/
    }

    //Declaração do método setY
    public void setY(int y) {
        this.y = y;
        // Altera Y para o valor passado
    }

    //Declaração do método setXY
    public void setXY(int x, int y) {
        this.y = y;
        this.x = x;
        // Altera x e y para os parametros passado
    }

    //Declaração do método distance
    public double distance(int x, int y) {
        int diferencaX = this.x - x;
        int diferencaY = this.y - y;
        return Math.sqrt((Math.pow(diferencaX, 2)) + (Math.pow(diferencaY, 2)));
        /* Retorna a distancia entre o MyPoint e os parametros passados
         * @return Double - valor da distancia*/

    }

    //Declaração do método distance
    public double distance(MyPoint newMyPoint) {
        int diferencaX = newMyPoint.getX() - this.getX();
        int diferencaY = newMyPoint.getY() - this.getY();
        return Math.sqrt((Math.pow(diferencaX, 2)) + (Math.pow(diferencaY, 2)));
        /* Retorna a distancia entre o MyPoint e outro MyPoint passado
         * @return Double - valor da distancia*/
    }

    //Declaração do método toString
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
        /* Retorna os parametros de MyPoint
         * @return String - coordenadas*/
    }
}
