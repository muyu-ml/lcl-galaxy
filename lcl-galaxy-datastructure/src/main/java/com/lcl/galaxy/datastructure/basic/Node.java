package com.lcl.galaxy.datastructure.basic;

import lombok.Data;

@Data
public class Node {
    private String data;
    private Node next;

    public Node(String data){
        this.data = data;
        next = null;
    }


    /**
     * 实现构造函数和实例变量
     * 请在Node类中开始完成节点的自实现！
     *
     * 创建一个基本节点类，包含：
     * 数据，指定为String类型
     * 指向下一个节点的链接，默认值为null
     * 创建构造函数
     * 使用说明
     * 1、给Node类创建实例变量
     *
     * 在类的顶部，声明两个public类型的属性String data和Node next。
     *
     * 2、由于节点包含数据，因此创建一个接收String data参数的构造函数
     *
     * 3、在有了构造函数和实例变量后，给变量赋值。在构造函数内部：
     *
     * 将类的data变量设置为传入的data
     * 将next属性设置为null
     * 每一个新的Node应有next属性默认为null，因为它没有被分配一个next Node。
     *
     * 4、验证结果：在main方法中，使用构造函数创建一个Node节点，变量名为firstNode，传入参数为 "I am a Node!"
     *
     * 打印出firstNode的数据检查设置是否正确。然后打印出它的下一个节点以检查它是否设置为null。您可以使用System.out.println()进行打印。
     */
    public static void main1(String[] args) {
        Node firstNode = new Node("I am a Node!");
        System.out.println(firstNode.data);
        System.out.println(firstNode.next);
    }

    /**
     * 设置下一个节点
     * 当前Node类创建的对象的next Node都是默认设置值null。但是，我们希望允许next更新属性，以便可以遍历一系列节点并将其用于更复杂的数据结构中。为此，我们将创建一个setter方法来修改this.next属性。
     *
     * 该方法将被调用.setNextNode()并以Node node作为参数，从而更新属性next。
     *
     * 使用说明
     * 1、在Node类中创建方法.setNextNode()，以Node node作为参数。此方法将不返回任何内容，因此应为public void。
     *
     * 2、在方法内部，将Node的next属性设置为node参数。
     *
     * 3、为了验证我们的.setNextNode()是否符合预期，让我们在Node实例上调用方法。
     *
     * 在main方法内部，创建一个第二个Node实例其实例名为secondNode，数据为："I am the second Node!"。将firstNode的下一个节点设置为secondNode。
     *
     * 打印出firstNode下一个节点的数据。您应该看到第二个节点实例，而不是默认null值。
     */
    public static void main2(String[] args) {
        Node firstNode = new Node("I am a Node!");
        Node secondNode = new Node("I am the second Node!");
        firstNode.setNextNode(secondNode);
        System.out.println(firstNode.next);
        System.out.println(firstNode.next.data);
    }

    public void setNextNode(Node node){
        this.next = node;
    }

    /**
     获取下一个节点
     尽管我们可以通过next属性直接访问下一个node节点，但通过方法封装访问变量才更安全。

     创建一个简单的.getNextNode()方法，该方法将返回next属性的node节点。

     使用说明
     1、在Node类中创建方法.getNextNode()。此方法将返回Node，因此是一个public Node方法。

     在方法内部，返回next属性对应的节点。

     2、有了next实例变量的setter和getter方法，再将public变量更改为private，确保永远无法在Node类之外访问它。

     在类的顶部，更改next为private变量。

     3、打印firstNode的.getNextNode()获取到的节点的数据来验证该方法是否返回了正确的next节点属性。

     我们应该注意，由于我们的main方法位于Node类中，因此仍然可以next直接访问该属性。在Node类外的任何地方都不可能做到这一点。
     */
    public static void main3(String[] args) {
        Node firstNode = new Node("I am a Node!");
        Node secondNode = new Node("I am the second Node!");
        firstNode.setNextNode(secondNode);
        System.out.println(firstNode.getNextNode());
        System.out.println(firstNode.getNextNode().getData());
    }

    public Node getNextNode(){
        return this.next;
    }

    public String getData(){
        return data;
    }

    /**
     想象一下，一家冰淇淋店出售三种口味：草莓，香蕉和椰子。新员工记不住用户下的订单，不知道先做草莓还是香蕉，做完后下一个做什么时，我们的Java节点可以帮到他们。

     使用说明
     1，在main方法中，实例化三个节点：

     第一个节点代表1号订单，名称为"1号订单-strawberry"。将节点分配给变量strawberry。
     第二个节点代表2号订单，名称为"2号订单-banana"。将节点分配给变量banana。
     第三个节点代表3号订单，名称为"3号订单-coconut"。将节点分配给变量coconut。

     2，现在，让我们依次排列这些冰淇淋节点。先制作strawberry再制作banana最后制作coconut。

     在新创建的节点下，使用您的.setNextNode()方法，以便让当前节点知道下一个节点该是谁：

     strawberry的next节点是banana
     banana的next节点是coconut

     3，让我们浏览一下冰淇淋节点，以确保它们以正确的顺序链接。

     创建一个新currentNode变量并将其设置为strawberry。

     创建一个while仅在currentNode不是null时进行循环。在while循环内，打印currentNode的data，然后更新currentNode到其下一个节点。

     我们应该会看到1号订单，2号订单和3号订单的制作顺序和内容。
     */
    public static void main(String[] args) {
        Node strawberry = new Node("1号订单-strawberry");
        Node banana = new Node("2号订单-banana");
        Node coconut = new Node("3号订单-coconut");
        strawberry.setNextNode(banana);
        banana.setNextNode(coconut);

        Node currentNode = strawberry;
        while (currentNode != null) {
            System.out.println(currentNode);
            System.out.println(currentNode.getData());
            currentNode = currentNode.next;
        }
    }
}
