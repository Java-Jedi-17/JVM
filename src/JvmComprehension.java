public class JvmComprehension {

    public static void main(String[] args) {
        int i = 1;                      // 1
        Object o = new Object();        // 2
        Integer ii = 2;                 // 3
        printAll(o, i, ii);             // 4
        System.out.println("finished"); // 7
    }

    private static void printAll(Object o, int i, Integer ii) {
        Integer uselessVar = 700;                   // 5
        System.out.println(o.toString() + i + ii);  // 6
    }
}

/*

Сначала происходит обращение к подсистеме загрузчика классов.
Bootstrap ClassLoader - класс "System" (строки 7 и 6);
Application ClassLoader - класс "JvmComprehension".

Классы "JvmComprehension" и "System" подгружаются в Metaspace.
В момент обращения к методу main() создается фрейм в стеке.

1) Во фрейм main() помещается примитивная переменная i = 1.

2) Новый объект "new Object" создается в куче. Ссылка "о" на этот объект появляется в стеке
и ссылается на этот объект в куче.

3) Ссылочная переменная "Integer" создается в куче. Ссылка "ii = 2" создается в стеке
в тот же фрейм "main" и ссылается на свой объект "Integer".

4) Метод "printAll" создается новым фреймом в стеке.
В этот фрейм помещаются:
  - ссылка "о" на "Object";
  - копия примитивной переменной "i = 1";
  - ссылка "ii" на объект "Integer";

5) Во фрейм "printAll" добавляется ссылка "uselessVar = 700"
на объект в куче "Integer".

6) Создается новый фрейм "println", в котором создается ссылка
на объект "String", создается копия примитивной переменной "i" и ссылка "ii" на объект
"Integer".

7) Создается новый фрейм в стеке "println" со ссылкой "finished" на объект "String".

   Сборщик мусора отчистит память переменных и объектов после 6 строки,
   потому что после нее они не используются.
   Память объекта "Integer" со ссылкой "uselessVar" отчистится сразу, так как
   нигде не используется.

*/