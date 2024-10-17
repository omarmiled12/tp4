package tp_point;

public class Mot_dict {

private String Mot;

private String Définition ;

public Mot_dict(String m , String d ) {

Mot=m;

Définition=d;

}


public String getMot() {

return this.Mot;

};

public String getDéfinition () {

return this.Définition ;

}

public void setDéfinition(String d) {

this.Définition=d;

}

public void setMot(String m) {

this.Mot=m;

}

public boolean synonyme (String a) {

return this.Mot.equals(a);

}

}
public class Dictionnaire {



    private int nb_mots;

    private Mot_dict[] Dict;

    private String Nom;



    public Dictionnaire(int n, String nom) {

        this.nb_mots=0;

        this.Dict=new Mot_dict[n];

        this.Nom=nom;

    }



    public void Ajouter_Mot(Mot_dict mot) {

        if (nb_mots<Dict.length) {

            Dict[nb_mots++]=mot;

            Trier();

        } else {

            System.out.println("Le dictionnaire est plein.");

        }

    }



    public void Trier() {

        for (int i=1; i<nb_mots; i++) {

            Mot_dict ch=Dict[i];

            int j=i-1;



            while (j>=0 && Dict[j].getMot().compareTo(ch.getMot())>0) {

                Dict[j+1]=Dict[j];

                j=j-1;

            }

            Dict[j+1]=ch;

        }

    }



    public void Supprimer_Mot(Mot_dict mot) {

        boolean ok=false;

        Mot_dict[] ch=new Mot_dict[Dict.length];

        int k=0;



        for (int i=0;i<nb_mots;i++) {

            if (!Dict[i].getMot().equals(mot.getMot())) {

                ch[k++]=Dict[i];

            } else {

                ok=true;

            }

        }



        if (ok) {

         Dict=ch ;

   nb_mots--;

        } else {

            System.out.println("Mot non trouvé.");

        }

    }



    public String Recherche_dicho(String m) {

        int g = 0;

        int d = nb_mots - 1;



        while (g <= d) {

            int middle = (g + d) / 2;

            String motM = Dict[middle].getMot();

            int comp = motM.compareTo(m);



            if (comp == 0) {

                return Dict[middle].getDéfinition();

            } else if (comp < 0) {

                g = middle + 1;

            } else {

                d = middle - 1;

            }

        }

        return "Mot non trouvé.";

    }



    public void Lister_dictionnaire() {

        if (nb_mots == 0) {

            System.out.println("Le dictionnaire est vide.");

        } else {

            System.out.println("Contenu du dictionnaire :");

            for (int i = 0; i < nb_mots; i++) {

                System.out.println(Dict[i].getMot() + " : " + Dict[i].getDéfinition());

            }

        }

    }



    public int Nombre_synonyme(Mot_dict mot) {

        int nb = 0;

        for (int i = 0; i < nb_mots; i++) {

            if (mot.synonyme(Dict[i].getMot())) {

                nb++;

            }

        }

        return nb;

    }



    public static void main(String[] args) {

        // Create dictionary

        Dictionnaire dictionnaire = new Dictionnaire(10, "Mon Dictionnaire");



        // Create words

        Mot_dict mot1 = new Mot_dict("Bonjour", "Salutation en français");

        Mot_dict mot2 = new Mot_dict("Au revoir", "Forme de salutation pour dire adieu");

        Mot_dict mot3 = new Mot_dict("Merci", "Expression de gratitude");

        Mot_dict mot4 = new Mot_dict("Salut", "Façon informelle de dire bonjour");



        // Add words to the dictionary

        dictionnaire.Ajouter_Mot(mot1);

        dictionnaire.Ajouter_Mot(mot2);

        dictionnaire.Ajouter_Mot(mot3);

        dictionnaire.Ajouter_Mot(mot4);



        // List all words

        System.out.println("Contenu du dictionnaire:");

        dictionnaire.Lister_dictionnaire();



        // Search for a word

        System.out.println("\nRecherche de 'Merci':");

        System.out.println(dictionnaire.Recherche_dicho("Merci"));



        // Count synonyms

        System.out.println("\nNombre de synonymes de 'Salut':");

        System.out.println(dictionnaire.Nombre_synonyme(mot4));



        // Remove a word

        dictionnaire.Supprimer_Mot(mot2);



        // List all words again after removal

        System.out.println("\nContenu du dictionnaire après suppression:");

        dictionnaire.Lister_dictionnaire();

    }

}