public class Triangle extends Shape{
    
    double height;
    double base;

    Triangle(double base,double height){
        this.base = base;
        this.height = height;
    }

    @Override
    double area(){
        return 0.5 * height * base;
    }
}
