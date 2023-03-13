package internship;

import java.util.ArrayList;
import java.util.List;

public class SequencesImpl implements Sequences{
    private List<Integer> list ;
    public void a(int n){
        list = new ArrayList<>();
        for(int res=2, i =1;i<=n;res+=2,i++){
            list.add(res);
        }
        System.out.print(list);
    }

    public void b(int n){
        list = new ArrayList<>();
        for(int res=1,i=1;i<=n;res+=2,i++){
            list.add(res);
        }
        System.out.print(list);
    }

    public void c(int n){
        list = new ArrayList<>();
        for(int i=1;i<=n;i++){
            list.add(i * i);
        }
        System.out.print(list);
    }

    public void d(int n){
        list = new ArrayList<>();
        for(int i=1;i<=n;i++){
            list.add(i * i * i);
        }
        System.out.print(list);
    }

    public void e(int n){
        list = new ArrayList<>();
        for(int i=1;i<=n;i++){
            if(i%2!=0){
                list.add(1);
            }
            else
            {
                list.add(-1);
            }
        }
        System.out.print(list);
    }

    public void f(int n){
        list = new ArrayList<>();
        int count = 0;
        for(int i=1,j=-2;count<n;i+=2,j-=2){
                list.add(i);
            if(++count<n)
                list.add(j);
            count++;
        }
        System.out.print(list);
    }

    public void g(int n){
        list = new ArrayList<>();
        for(int i=1;i<=n;i++){
            if(i%2!=0){
                list.add(i * i);
            }
            else
            {
                list.add(i * i);
            }
        }
        System.out.print(list);
    }

    public void h(int n){
        list = new ArrayList<>();
        int seq = 1;
        for(int i=1;i<=n;i++) {
            if(i%2!=0){
                list.add(seq);
                seq++;
            }
            else
            {
                list.add(0);
            }
        }
        System.out.print(list);
    }

    public void i(int n){
        list = new ArrayList<>();
        int mult = 1;
        for(int i=1;i<=n;i++){
            mult *= i;
            list.add(mult);
        }
        System.out.print(list);
    }

    public void j(int n){
        list = new ArrayList<>();
        int n0 = 1;
        int n1 = 1;
        int n2;
        list.add(n0);
        if(n>1) {
            list.add(n1);
            for (int i = 3; i <= n; i++) {
                n2 = n0 + n1;
                list.add(n2);
                n0 = n1;
                n1 = n2;
            }
        }
        System.out.print(list);
    }

}
