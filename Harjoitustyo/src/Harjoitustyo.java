import java.util.*;

public class Harjoitustyo {

    //Quick sort
    static void quickSort(int[] arr, int lo, int hi)
    { 
        int i = lo, j = hi, h; 
        int pivot = arr[(lo + hi) / 2]; //Valitaan pivotiksi keskimmäinen alkio
        
        do{
            while(arr[i] < pivot)i++;   //Etsitään oikealta alkio, joka on pivottia suurempi
            while(arr[j] > pivot)j--;   //Sama vasemmalle puolelle
            if(i<=j){   //Vaihdetaan alkioiden paikkaa
                h = arr[i];
                arr[i] = arr[j];
                arr[j] = h;
                i++;
                j--;
            }
        }while(i <= j); //Jatketaan kunnes lista käyty läpi

        //Rekursio oikealle ja vasemmalle puolelle listaa
        if(lo < j) quickSort(arr, lo, j);
        if(i < hi) quickSort(arr, i, hi);

    }
    static void quickSortTest(int[] arr){
        quickSort(arr, 0, arr.length - 1);
    }
    

    //Insertion sort
    static void insertionSort(int arr[])
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) { //Etsitään oikea paikka alkioille
                arr[j + 1] = arr[j]; //Siirretään suurempia alkioita oikealle
                j = j - 1;
            }
            arr[j + 1] = key; //Asetetaan alkio oikeaan kohtaan
        }
    }


    //Merge sort
    static void mergeSort(int arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i]; //Kopioidaan vasen puolisko
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j]; //Kopioidaan oikea puolisko
        
        int i = 0, j = 0;

        int k = l;
        while(i < n1 && j < n2) {
            if (L[i] <= R[j]){
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) { //Kopioidaan loput vasemmalta
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) { //Kopioidaan loput oikealta
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    static void sort(int arr[], int l, int r) {
        if (l < r){
            int m = l + (r - l) / 2;
            sort(arr, l, m);
            sort(arr, m + 1, r);
            mergeSort(arr, l, m, r);
        }
    }
    static void mergeSortTest(int[] arr){
        sort(arr, 0, arr.length - 1);
    }


    //Selection sort 
    static void selectionSort(int[] arr){
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min_idx]) { //Etsitään pienin alkio
                    min_idx = j;
                }
            }
            int temp = arr[i]; //Vaihdetaan pienin alkio nykyiseen paikkaan
            arr[i] = arr[min_idx];
            arr[min_idx] = temp;           
        }
    }


    //Bubble sort
    static void bubbleSort(int[] arr) {
        int n = arr.length;
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i- 1; j++) {
                if (arr[j] > arr[j + 1]) { //Vaihdetaan vierekkäisiä alkioita

                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (swapped == false) //Lopetetaan jos ei enään vaihtoja
            break;
        }
    }


    //Shell sort
    static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2){ // Alustetaan väliarvo
            for (int i = gap; i < n; i++){
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap){ // Siirretään alkioita
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp; //Asetetaan alkio oikeaan kohtaan
            }
        }
    }

    //Array tulostus testaamista varten
    static void printArray(int[] arr){
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    //Satunnainen array
    static int[] createRandomArray(int N) {
        int[] arr = new int[N];

        Random gen = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = gen.nextInt(N);
        }
        return arr;
    }

    //Nouseva array
    static int[] createAscendingArray(int N) {
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = i;
        }

        return arr;
    }  

    //Laskeva array
    static int[] createDescendingArray(int N) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = N - i - 1;
        }
        return arr;
    }


    //Funktiopointteri
    private interface FunctionPointer {
        void methodSignature(int[] arr);
    }

    public static void SortMethodTester(FunctionPointer methodToBeTested, String methodName, int n)
    {
        int[] array = createRandomArray(n);
        int[] ASarray = createAscendingArray(n);
        int[] DESarray = createDescendingArray(n);
        
        long start = System.nanoTime();
        methodToBeTested.methodSignature(array);
        long end = System.nanoTime();
        double kesto = (end - start) / 1000000.0;
        System.out.println("Random array | " + methodName + ": " + kesto + " ms");

        long start2 = System.nanoTime();
        methodToBeTested.methodSignature(ASarray);
        long end2 = System.nanoTime();
        double kesto2 = (end2 - start2) / 1000000.0;
        System.out.println("Ascending array | " + methodName + ": " + kesto2 + " ms");

        long start3 = System.nanoTime();
        methodToBeTested.methodSignature(DESarray);
        long end3 = System.nanoTime();
        double kesto3 = (end3 - start3) / 1000000.0;
        
        System.out.println("Descending array | " + methodName + ": " + kesto3 + " ms\n");
    }
  
    public static void main(String[] args){

        int N = 500000;
        
        FunctionPointer SelectionSort = Harjoitustyo::selectionSort;
        FunctionPointer InsertionSort = Harjoitustyo::insertionSort;
        FunctionPointer BubbleSort = Harjoitustyo::bubbleSort;
        FunctionPointer ShellSort = Harjoitustyo::shellSort;
        FunctionPointer QuickSort = Harjoitustyo::quickSortTest;
        FunctionPointer MergeSort = Harjoitustyo::mergeSortTest;
        FunctionPointer ArraySort = Arrays::sort;

        SortMethodTester(SelectionSort, "Selection sort", N);
        SortMethodTester(InsertionSort, "Insertion sort", N);
        SortMethodTester(BubbleSort, "Bubble sort", N);
        SortMethodTester(ShellSort, "Shell sort", N);
        SortMethodTester(QuickSort, "Quick sort", N);
        SortMethodTester(MergeSort, "Merge sort", N);
        SortMethodTester(ArraySort, "Array sort", N);
    }    
}
