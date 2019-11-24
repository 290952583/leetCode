package main.java.com.xux.daily;

public class L21_Meger_two_sorted_Lists {
    public static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode head=new ListNode(0);
        ListNode node=head;
        ListNode curr=null;
        while(l1!=null && l2!=null){
            if(l1.val<l2.val){
                curr=new ListNode(l1.val);
                l1=l1.next;
            }else{
                curr=new ListNode(l2.val);
                l2=l2.next;
            }
            node.next=curr;
            node=node.next;
        }
        while(l1!=null){
            node.next=new ListNode(l1.val);
            l1=l1.next;
            node=node.next;
        }
        while(l2!=null){
            node.next=new ListNode(l2.val);
            l2=l2.next;
            node=node.next;
        }
        return head.next;
    }

    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        // 依次遍历两个链表
        ListNode head = new ListNode(0);

        ListNode pre = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        pre.next = l1 == null ? l2 : l1;

        return head.next;
    }

    public static ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }

        if(l1.val < l2.val) {
            l1.next = mergeTwoLists3(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists3(l1, l2.next);
            return l2;
        }
    }


    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next = new ListNode(4);

        ListNode listNode1 = mergeTwoLists1(l1, l2);
        ListNode listNode2 = mergeTwoLists2(l1, l2);
        ListNode listNode3 = mergeTwoLists3(l1, l2);
    }

}
