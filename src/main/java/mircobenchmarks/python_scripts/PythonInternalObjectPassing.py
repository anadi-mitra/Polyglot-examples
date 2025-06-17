def sliceList(head):
    if head is None:
        print( "head was null")
    else:
        makeNewList(head)   #function call

def makeNewList(head):
    tmp=head.next
    #find a value which is greater than head
    while tmp is not None and head.value>tmp.value:
        tmp=tmp.next

    #if no element is greater than head
    if tmp is None:
        #Object pointed by 'head' escaped here
        print("assigning head to global variable")
        SomeSharedClass.A_GLOBAL_NODE=head
    else: #print the list
        print(head.value)

class SomeSharedClass:
    A_GLOBAL_NODE: object