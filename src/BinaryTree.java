// imports para a fila usada na levelOrderTraversal(BTNode node). 
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

	protected BTNode root;

	public BinaryTree() {
		this(null);
	}

	public BinaryTree(BTNode root) {
		this.root = root;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public int getDegree() {
		return getDegree(root);
	}

	private int getDegree(BTNode node) {
		if (node == null || node.isLeaf()) {
			return 0;
		}

		int degree = node.getDegree();
		
		if (node.hasLeftChild()) {
			degree = Math.max(degree, getDegree(node.getLeft()));
		}
		
		if (node.hasRightChild()) {
			degree = Math.max(degree, getDegree(node.getRight()));
		}
		
		return degree;
	}

	public int getHeight() {
		if (isEmpty()) {
			return -1;
		}

		return root.getHeight();
	}
	
	public BTNode getRoot() { 
	    return root;
	}
	
	public BTNode search(int data) {
        return search(root, data);
    }

    private BTNode search(BTNode node, int data) {
        if (node == null) {
            return null;
        }
        int diff = Integer.compare(data, node.getCod());
        if (diff < 0) {
            return search(node.getLeft(), data);
        } else if (diff > 0) {
            return search(node.getRight(), data);
        } else {
            return node;
        }
    }
	
	public void insert(int data, BTNode dados) {
        root = insert(root, null, data,dados);
        // Verificação após a inserção
        if (root.getParent() != null) {
            root = root.getParent();
        }
        root.setParent(null); // Garantir que o parent do root seja null
    }

    private BTNode insert(BTNode node, BTNode parent, int data, BTNode dados) {
        if (node == null) {
            dados.setParent(parent);
            return dados;
        }
        int diff = Integer.compare(data, node.getCod());
        if (diff < 0) {
            node.setLeft(insert(node.getLeft(), node, data,dados));
        } else if (diff > 0) {
            node.setRight(insert(node.getRight(), node, data,dados));
        } else {
            node.setRep(1);
        }
        return node;
    } 
    
    public void remove(int data) {
        if(search(root,data)!= null){
            System.out.println("\nValor "+ data +" foi removido!\n");
        }
        root = remove(root,data);
        // Verificação após a remoção
        if (root != null && root.getParent() != null) {
            root = root.getParent();
        }
        if (root!= null){
            root.setParent(null);
        }
        
    }
	
	public BTNode remove(BTNode node, int data){
	    if(node == null){
	        return node;
	    }
	    if(data>node.getCod()){
	        root.setRight(remove(root.getRight(),data));
	    }
	    else if(data<node.getCod()){
	        root.setLeft(remove(root.getLeft(),data));    
	    }
	    else{
	        if(node.isLeaf()){
	            if(node.getParent().getLeft().getCod()==data){
	                node.getParent().setLeft(null);
	            }else{
	                node.getParent().setRight(null);
	            }
	        }
	        else if(node.getRight()!=null){
	            node.setCod(maior(node));
	            node.setRight(remove(node.getRight(),node.getCod()));
	        }else{
	            node.setCod(menor(node));
	            node.setLeft(remove(node.getLeft(),node.getCod()));
	        }
	    }
	    return node;
	}
	
	public int maior(BTNode node){
	    root = root.getRight();
	    while(root.getLeft()!=null){
	        root = root.getLeft();
	    }
	    return root.getCod();
	}
	
	public int menor(BTNode node){
	    root = root.getLeft();
	    while(root.getRight()!= null){
	        root = root.getRight();
	    }
	    return root.getCod();
	}
	
	public String analise() { 
	    BTNode maiorEI = MaiorEI(root);
	    BTNode maiorCE = MaiorCE(root);
	    BTNode maiorSR = MaiorSR(root);
	    BTNode maiorAI = MaiorAI(root);
	    BTNode maiorAF = MaiorAF(root);
	    BTNode maiorEM = MaiorEM(root);
	    BTNode maiorEJAFAI = MaiorEJAFAI(root);
	    BTNode maiorEJAFAF = MaiorEJAFAF(root);
	    BTNode maiorEJAEM = MaiorEJAEM(root);
	    
	    return "A escola " + maiorEI.getNome() + " tem o maior numero de alunos na Educação Infantil, tendo " + maiorEI.getEI() + " alunos."+
    "\nA escola " + maiorCE.getNome() + " tem o maior numero de alunos nas Classes Especiais, tendo " + maiorCE.getCE() + " alunos."+
    "\nA escola " + maiorSR.getNome() + " tem o maior numero de alunos na Sala de Recursos, tendo " + maiorSR.getSR() + " alunos."+
    "\nA escola " + maiorAI.getNome() + " tem o maior numero de alunos nos Anos Iniciais, tendo " + maiorAI.getAI() + " alunos."+
    "\nA escola " + maiorAF.getNome() + " tem o maior numero de alunos nos Anos Finais, tendo " + maiorAF.getAF() + " alunos."+
    "\nA escola " + maiorEM.getNome() + " tem o maior numero de alunos no Ensino Médio, tendo " + maiorEM.getEM() + " alunos."+
    "\nA escola " + maiorEJAFAI.getNome() + " tem o maior numero de alunos no EJA Anos Iniciais, tendo " + maiorEJAFAI.getEJAFAI() + " alunos."+
    "\nA escola " + maiorEJAFAF.getNome() + " tem o maior numero de alunos no EJA Anos Finais, tendo " + maiorEJAFAF.getEJAFAF() + " alunos."+
    "\nA escola " + maiorEJAEM.getNome() + " tem o maior numero de alunos no EJA Ensino Médio, tendo " + maiorEJAEM.getEJAEM() + " alunos."+
        "\n";
	}
	
	private BTNode MaiorEI(BTNode node) {
	    if (node == null) {
	        return null; 
	    } 
	    BTNode maior = node; 
	    BTNode esq = MaiorEI(node.getLeft()); 
	    BTNode dir = MaiorEI(node.getRight());
	    if (esq != null && Integer.parseInt(esq.getEI()) > Integer.parseInt(maior.getEI())) {
	        maior = esq; 
	    } 
	    if (dir != null && Integer.parseInt(dir.getEI()) > Integer.parseInt(maior.getEI())) { 
	        maior = dir; 
	    } 
	    return maior; 
	}
	
	private BTNode MaiorCE(BTNode node) {
	    if (node == null) {
	        return null; 
	    } 
	    BTNode maior = node; 
	    BTNode esq = MaiorCE(node.getLeft()); 
	    BTNode dir = MaiorCE(node.getRight());
	    if (esq != null && Integer.parseInt(esq.getCE()) > Integer.parseInt(maior.getCE())) {
	        maior = esq; 
	    } 
	    if (dir != null && Integer.parseInt(dir.getCE()) > Integer.parseInt(maior.getCE())) { 
	        maior = dir; 
	    } 
	    return maior; 
	}
	
	private BTNode MaiorSR(BTNode node) {
	    if (node == null) {
	        return null; 
	    } 
	    BTNode maior = node; 
	    BTNode esq = MaiorSR(node.getLeft()); 
	    BTNode dir = MaiorSR(node.getRight());
	    if (esq != null && Integer.parseInt(esq.getSR()) > Integer.parseInt(maior.getSR())) {
	        maior = esq; 
	    } 
	    if (dir != null && Integer.parseInt(dir.getSR()) > Integer.parseInt(maior.getSR())) { 
	        maior = dir; 
	    } 
	    return maior; 
	}
	
	private BTNode MaiorAI(BTNode node) {
    if (node == null) {
        return null;
    }
    BTNode maior = node;
    BTNode esq = MaiorAI(node.getLeft());
    BTNode dir = MaiorAI(node.getRight());

    if (esq != null && Integer.parseInt(esq.getAI()) > Integer.parseInt(maior.getAI())) {
        maior = esq;
    }
    if (dir != null && Integer.parseInt(dir.getAI()) > Integer.parseInt(maior.getAI())) {
        maior = dir;
    }
    return maior;
}
    
    private BTNode MaiorAF(BTNode node) {
        if (node == null) {
            return null;
        }
        BTNode maior = node;
        BTNode esq = MaiorAF(node.getLeft());
        BTNode dir = MaiorAF(node.getRight());
    
        if (esq != null && Integer.parseInt(esq.getAF()) > Integer.parseInt(maior.getAF())) {
            maior = esq;
        }
        if (dir != null && Integer.parseInt(dir.getAF()) > Integer.parseInt(maior.getAF())) {
            maior = dir;
        }
        return maior;
    }

	private BTNode MaiorEM(BTNode node) {
        if (node == null) {
            return null;
        }
        BTNode maior = node;
        BTNode esq = MaiorEM(node.getLeft());
        BTNode dir = MaiorEM(node.getRight());
    
        if (esq != null && Integer.parseInt(esq.getEM()) > Integer.parseInt(maior.getEM())) {
            maior = esq;
        }
        if (dir != null && Integer.parseInt(dir.getEM()) > Integer.parseInt(maior.getEM())) {
            maior = dir;
        }
        return maior;
    }

	private BTNode MaiorEJAFAI(BTNode node) {
        if (node == null) {
            return null;
        }
        BTNode maior = node;
        BTNode esq = MaiorEJAFAI(node.getLeft());
        BTNode dir = MaiorEJAFAI(node.getRight());
    
        if (esq != null && Integer.parseInt(esq.getEJAFAI()) > Integer.parseInt(maior.getEJAFAI())) {
            maior = esq;
        }
        if (dir != null && Integer.parseInt(dir.getEJAFAI()) > Integer.parseInt(maior.getEJAFAI())) {
            maior = dir;
        }
        return maior;
    }

	private BTNode MaiorEJAFAF(BTNode node) {
        if (node == null) {
            return null;
        }
        BTNode maior = node;
        BTNode esq = MaiorEJAFAF(node.getLeft());
        BTNode dir = MaiorEJAFAF(node.getRight());
    
        if (esq != null && Integer.parseInt(esq.getEJAFAF()) > Integer.parseInt(maior.getEJAFAF())) {
            maior = esq;
        }
        if (dir != null && Integer.parseInt(dir.getEJAFAF()) > Integer.parseInt(maior.getEJAFAF())) {
            maior = dir;
        }
        return maior;
    }

	private BTNode MaiorEJAEM(BTNode node) {
        if (node == null) {
            return null;
        }
        BTNode maior = node;
        BTNode esq = MaiorEJAEM(node.getLeft());
        BTNode dir = MaiorEJAEM(node.getRight());
    
        if (esq != null && Integer.parseInt(esq.getEJAEM()) > Integer.parseInt(maior.getEJAEM())) {
            maior = esq;
        }
        if (dir != null && Integer.parseInt(dir.getEJAEM()) > Integer.parseInt(maior.getEJAEM())) {
            maior = dir;
        }
        return maior;
    }

	public void avRemove(){
        avRemoveAux(root);
        root = null;
    }
        
    public void avRemoveAux(BTNode node){
        if(node.getLeft() != null){
        avRemoveAux(node.getLeft());
        }
        if(node.getRight() != null){
        avRemoveAux(node.getRight());
        }
        node.setCod(-1);
        node.setNome("");
        node.setEI("");
        node.setCE("");
        node.setSR("");
        node.setAI("");
        node.setAF("");
        node.setEM("");
        node.setEJAFAI("");
        node.setEJAFAF("");
        node.setEJAEM("");
        node.setRight(null);
        node.setLeft(null);
        node.setParent(null);
    }

	public String inOrderTraversal() {
		StringBuilder sb = new StringBuilder();
		inOrderTraversal(sb, root);
		return sb.toString();
	}

	private void inOrderTraversal(StringBuilder sb, BTNode node) {
		if (node == null) {
			return;
		}

		// Em ordem = percurso LNR.
		inOrderTraversal(sb, node.getLeft());
		sb.append(node + "\n");
		inOrderTraversal(sb, node.getRight());
	}

	public void preOrderTraversal() {
		preOrderTraversalhelper(root);
		return;
	}

	private void preOrderTraversalhelper(BTNode node) {
		if (node == null) {
			return ;
		}
		// Pré-ordem = percurso NLR.
		System.out.println(
			    "BinaryTree - " + node.getCod() +
			    ", getDegree(): " + node.getDegree() +
			    ", getHeight(): " + node.getHeight() +
			    ", parent => {" + (node.getParent() != null ? node.getParent().getCod() : "null") + "}" +
			    ", right => {" + (node.getRight() != null ? node.getRight().getCod() : "null") + "}" +
			    ", left => {" + (node.getLeft() != null ? node.getLeft().getCod() : "null") + "}"
			);
		//System.out.println(node.getData()+" ");
		preOrderTraversalhelper(node.getLeft());
		preOrderTraversalhelper(node.getRight());
	}

	public String postOrderTraversal() {
		StringBuilder sb = new StringBuilder();
		postOrderTraversal(sb, root);
		return sb.toString();
	}

	private void postOrderTraversal(StringBuilder sb, BTNode node) {
		if (node == null) {
			return;
		}

		// Pós-ordem = percurso LRN.
		postOrderTraversal(sb, node.getLeft());
		postOrderTraversal(sb, node.getRight());
		sb.append(node + "\n");
	}

	public String levelOrderTraversal() {
		if (isEmpty()) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();

		Queue<BTNode> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			BTNode visited = queue.remove();
			sb.append(visited + "\n");

			if (visited.hasLeftChild()) {
				queue.add(visited.getLeft());
			}			
			if (visited.hasRightChild()) {
				queue.add(visited.getRight());
			}
		}
		
		return sb.toString();
	}

	// Apenas para uma exibição melhor da árvore.
	public String inReversedOrderAscii() {
		StringBuilder sb = new StringBuilder();
		inReversedOrderAscii(sb, root);
		return sb.toString();
	}

	private void inReversedOrderAscii(StringBuilder sb, BTNode node) {
		if (node == null) {
			return;
		}

		// Em ordem (invertida) = percurso RNL.

		// R
		if (!node.hasRightChild()) {
			for (int i = 0; i < node.getLevel() + 1; ++i) {
				sb.append("¦   ");
			}
			sb.append("├─» dir null\n");
		}
		inReversedOrderAscii(sb, node.getRight());

		// N
		for (int i = 0; i < node.getLevel(); ++i) {
			sb.append("¦   ");
		}
		sb.append("├─» " + node.getCod() + "\n");

		// L
		if (!node.hasLeftChild()) {
			for (int i = 0; i < node.getLevel() + 1; ++i) {
				sb.append("¦   ");
			}
			sb.append("├─» esq null\n");
		}
		inReversedOrderAscii(sb, node.getLeft());
	}

	@Override
	public String toString() {
		return "BinaryTree - isEmpty(): " + isEmpty()
				+ ", getDegree(): " + getDegree()
				+ ", getHeight(): " + getHeight()
				+ ", root => { " + root + " }";				
	}

}