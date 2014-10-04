import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

public class BfE extends Explanations
{
    private int num;
    private String[] sList = new String[LIMIT];
    GreenfootImage base = new GreenfootImage(getImage());
    GreenfootImage txt;
    public BfE(int No)
    {
        num = No;
        int i=0;
        sList[0] = new String("이 프로그램은 프로그래밍의 데이터 구조를\n시각적으로 표현한 프로그램입니다.\n\n이 프로그램을 통해 데이터가 어떤 식으로 출입하는지 알 수 있게 됩니다.\n\n데이터 구조의 종류로는 스택, 원형 큐와 선형 큐, 리스트, 트리가 있으며,\n이 프로그램에서는 앞서 말한 5가지만 다루었습니다.\n\n데이터에 적힌 숫자는 몇 번째에 들어온 데이터인가를 의미합니다.");
        sList[1] = new String("[ 버튼에 관한 설명 ]\n\n\n1. 집 모양 버튼은 초기 화면으로 돌아갑니다.\n\n2. 시작 버튼을 누르신 후 나타나는 4가지 버튼은 각 데이터 구조로 안내합니다.\n\n3. 버튼이 활성화 되어 있을 때에는 마우스를 갖다 댈 시 버튼이 노랗게 됩니다.\n\n4. List에서는 데이터 노드를 클릭하시면 동작 선택 상자가 나타납니다.");
        sList[2] = new String("[ 데이터 구조 설명 - 스택(Stack) ]\n\n\n스택은 후입선출(Last In First Out), 즉 나중에 온 데이터가 먼저\n나가는 방식으로 출입하는 형태의 데이터 구조입니다.\n\n스택의 경우 간단한 등비/등차수열의 원리로 익힐 수도 있으며, 원치 않을 경우\n엔터를 치시면 됩니다.\n\n좌측의 'SP'는 현재 데이터가 들어올 위치를 가리킵니다.\n\n등비수열 : 연속된 두 수의 비가 일정한 수열. 4개만 채우면 됩니다.\n\n등차수열 : 연속된 두 수의 차가 일정한 수열. 모두 채워야 합니다.");
        sList[3] = new String("[ 데이터 구조 설명 - 선형 큐(Linear Queue) ]\n\n\n큐는 선입선출(First In First Out), 즉 먼저 온 데이터가 먼저\n나가는 방식으로 출입하는 형태의 데이터 구조입니다.\n\n선형 큐의 경우에는 간단한 선형(線形)의 구조로 되어 있습니다.\n\n데이터가 나갈 자리를 'Front', 들어올 자리를 'Rear'라고 합니다.\n\n데이터 입력을 'EnQueue', 데이터 삭제를 'DeQueue'라고 합니다.");
        sList[4] = new String("[ 데이터 구조 설명 - 원형 큐(Circular Queue) ]\n\n\n원형 큐는 선형 큐와는 달리 순회하는 형태로 되어 있습니다. 이는\n배열 첨자의 순환을 의미합니다.\n\n원형 큐는 마지막으로 데이터가 들어올 수 있는 영역의 직전까지 입력되었을\n경우 데이터들이 가득 찬 것으로 인식합니다.\n\n용어는 선형 큐와 동일하며 클릭 후 이동이 멈춰야 버튼의 빛이 꺼집니다.\n\nOverFlow : 가득 찬 상태에서 데이터 입력 시도\nEmpty : 빈 상태에서 데이터 삭제 시도");
        sList[5] = new String("[ 데이터 구조 설명 - 리스트(List) ]\n\n리스트는 데이터가 구조체의 형태로 되어 있으며, 구조체가 가진 포인터를 통\n해 데이터가 연결되어 있습니다. \n\n이 프로그램에서는 간단한 형태인 연결리스트(Linked List)만을 다룹니다.\n\n리스트의 경우 데이터 구조체 하나를 '노드'라고 부르며, 이것을 삭제할 시\n이전 노드의 포인터에는 삭제하고자 하는 노드의 다음 노드가 연결되고\n곧바로 삭제하려는 노드는 제거됩니다.\n\n가장 처음의 노드를 'Head', 가장 마지막 노드를 'Tail'이라고 합니다.\nHead의 경우 데이터 추가만 가능하도록 설정하였습니다.\n본래 리스트는 무수히 많이 형성할 수 있으나 이 프로그램에서는 6개까지\n만 나옵니다.");
        sList[6] = new String("[ 데이터 구조 설명 - 힙(Heap) ]\n\n우선 트리라고 하는 데이터 구조에 대해 설명드리자면 트리란 근원, 즉 루트\n노드(Root Node)로부터 나뭇가지처럼 뻗어 나가는 형태의 데이터 구조입니다.\n힙이란 트리라고 하는 데이터 구조 중에서 가장 보편화되어 있는 완벽이진\n트리(모든 노드가 자녀 노드가 2개의 자녀 노드를 갖는 형태의 트리)\n입니다.(마지막은 왼쪽 자식만 있어도 됨)\n\n섞기 버튼은 MIN/MAX 힙이 완벽히 구축되어야 활성화됩니다.\n\n[i누르면 제거되고, 다시 p를 누르면 루트노드를 생성]\nMI N힙 : 부모 노드가 자식 노드보다 항상 작은 값을 가집니다.\nMAX 힙 : 부모 노드가 자식 노드보다 항상 큰 값을 가집니다.\nSHIFT DOWN : 이미 구축된 힙을 MIN/MAX힙으로 정렬\nSHIFT UP : 힙을 구축해 나가며 MIN/MAX힙으로 정렬");
    }
    public void act() 
    {
        if(!expFin)
        {
            printExp();
        }
        else
        {
            getWorld().removeObject(this);
        }
    }    
    private void printExp()
    {
        GreenfootImage img = new GreenfootImage(base);
        switch(num)
        {
            case 1:
                txt = new GreenfootImage(sList[num-1], 20, Color.BLACK, t);
                img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
                setImage(img);
                break;
            case 2:
                txt = new GreenfootImage(sList[num-1], 20, Color.BLACK, t);
                img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
                setImage(img);
                break;
            case 3:
                txt = new GreenfootImage(sList[num-1], 20, Color.BLACK, t);
                img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
                setImage(img);
                break;
            case 4:
                txt = new GreenfootImage(sList[num-1], 20, Color.BLACK, t);
                img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
                setImage(img);
                break;
            case 5:
                txt = new GreenfootImage(sList[num-1], 20, Color.BLACK, t);
                img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
                setImage(img);
                break;
            case 6:
                txt = new GreenfootImage(sList[num-1], 20, Color.BLACK, t);
                img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
                setImage(img);
                break;
            case 7:
                txt = new GreenfootImage(sList[num-1], 20, Color.BLACK, t);
                img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
                setImage(img);
                break;
        }
    }
    public void changeNum(int no)
    {
        num = no;
    }
}
