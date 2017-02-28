package edu.buffalo.cse116;
import java.util.List;

public class CardShoe {
  private List<PlayingCard> cards;

  public CardShoe(List<PlayingCard> originalList) {
    cards = originalList;
  }

  public void shuffle(ListGenerator gen) {
    List<PlayingCard> firstHalf = gen.createNewList();
    List<PlayingCard> secondHalf = gen.createNewList();
    
    //Adding first half of cards to firstHalf
    for(int i=0;i<cards.size()/2;i++){
    	firstHalf.add(cards.get(i));
    }
    //Adding second half of cards to secondHalf
    for(int i=cards.size()/2;i<cards.size();i++){
    	secondHalf.add(cards.get(i));
    }
    
    cards.clear();
    
    while(firstHalf.size()>0){
    	cards.add(firstHalf.get(0));
    	firstHalf.remove(0);
    	cards.add(secondHalf.get(0));
    	secondHalf.remove(0);
    }
    
    if(secondHalf.size()>0){
    	cards.add(secondHalf.get(0));
    	secondHalf.remove(0);
    }
  }
}
