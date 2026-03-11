/* Given a linked list head, reading the data from the first and last node, find the max of the pairs until the linked list is empty, the solution needs to be constant space
1->2->->1->1->8->4
1+4 = 5
2+8 = 10
1+1 = 2
max pairs are 2 and 8
*/

import java.util.*;
import java.io.*;
class Node{
    Node next=null;
    int val;
    
    public Node(int v){
        this.val=v;
    }
}
class Main {
    public static void main(String args[])
    {
        Node list1 = new Node(1);
        list1.next = new Node(2);
        list1.next.next = new Node(7);
        list1.next.next.next = new Node(9);
        list1.next.next.next.next = new Node(8);
        list1.next.next.next.next.next = new Node(4);

        // Creating the second list: 1 -> 2 -> 7 -> 8 -> 4
        Node list2 = new Node(1);
        list2.next = new Node(2);
        list2.next.next = new Node(7);
        list2.next.next.next = new Node(8);
        list2.next.next.next.next = new Node(4);
        
        Node slow=list1, fast=list1.next;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast= fast.next.next;
        }
        
        slow.next = reverse(slow.next);
        slow=slow.next;
        // printList(list1);
        Node head = list1;
        int max=Integer.MIN_VALUE;
        
        while(head!=null && slow!=null){
            if(head!=slow) max = Math.max(max,head.val+slow.val);
            else max = Math.max(max, head.val);
            head=head.next;
            slow=slow.next;
        }
        System.out.println(max);
    }
    
    static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.val + (temp.next != null ? " -> " : ""));
            temp = temp.next;
        }
        System.out.println();
    }
    
    static Node reverse(Node hd){
        Node cur=hd;
        Node prev=null;
        Node next=null;
        
        while(cur!=null){
            next = cur.next;
            cur.next=prev;
            prev=cur;
            cur=next;
        }
        
        return prev;
    }
    
}