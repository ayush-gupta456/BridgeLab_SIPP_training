package com.gla;

class NavNode {
    String url;
    NavNode prev, next;

    NavNode(String url) {
        this.url = url;
    }
}

class BrowHistory {
    NavNode curr;

    BrowHistory(String home) {
        curr = new NavNode(home);
    }

    void visit(String url) {
        NavNode nn = new NavNode(url);
        curr.next = null; // clear forward history
        nn.prev = curr;
        curr.next = nn;
        curr = nn;
    }

    String back(int steps) {
        while (steps > 0 && curr.prev != null) {
            curr = curr.prev;
            steps--;
        }
        return curr.url;
    }

    String forward(int steps) {
        while (steps > 0 && curr.next != null) {
            curr = curr.next;
            steps--;
        }
        return curr.url;
    }

    String getPage() {
        return curr.url;
    }
}

class MusicPlayerHis extends BrowHistory {
    MusicPlayerHis(String firstsong) {
        super(firstsong);
    }

    void play(String song) {
        visit(song);
        System.out.println("Now playing: " + getPage());
    }
}

public class BrowserHistory {
    public static void main(String[] args) {
        BrowHistory obj = new BrowHistory("BridgeLab.com");
        obj.visit("youtube.com");
        obj.visit("github.com");
        System.out.println("current page : "+obj.getPage());
        System.out.println("Back to: " + obj.back(2));   
        System.out.println("Forward to: " + obj.forward(1));
        MusicPlayerHis player = new MusicPlayerHis("Song A");
        player.play("Song B");
        player.play("Song C");
        player.back(1);
        System.out.println("Now playing: " + player.getPage());
    }
}
