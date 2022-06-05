class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Tire tire = new Tire();
        List<String> result = new ArrayList<>();
       
        Arrays.sort(folder);
        
        for(String s:folder){
            
            if(tire.insert(s)){
                result.add(s);
            }
        }
        return result;
    }
}

class Tire{
    
    class Node{
        Map<String,Node> map = new HashMap<String,Node>();
        boolean flag;
    }
    
    Node root;
    
    Tire(){
        root= new Node();
    }
    
    public boolean insert(String s){
        Node curr= root;
        StringBuilder builder= new StringBuilder();
        
        for(int i=1;i<=s.length();i++){
            
            if(i != s.length() && s.charAt(i) !='/')
            {
                builder.append(s.charAt(i));
                continue;
            }
            
            if(contains(builder.toString(),curr))
            {
                put(builder.toString(),new Node(),curr);
            }
            curr= get(builder.toString(),curr);
            builder= new StringBuilder();
            
            if(checkFlag(curr))
                return false;
        }
        setFlag(curr);
        return true;
    }
    
    public boolean contains(String s,Node node){
        return node.map.get(s) == null;
    }
              
     public void put(String s,Node node,Node curr){
         curr.map.put(s,node);
     }   
    
     public void setFlag(Node node){
         node.flag=true;
     } 
     
     public boolean checkFlag(Node node){
         return node.flag;
     }
    public Node get(String s,Node node){
        return node.map.get(s);
    }
}