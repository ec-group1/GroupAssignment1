package GeneticTSP;
public class SpeciesPopulation {
    SpeciesIndividual head;
    int speciesNum;
    SpeciesPopulation()
    {
        head=new SpeciesIndividual();
        speciesNum=TSPData.SPECIES_NUM;
    }
    void add(SpeciesIndividual species)
    {
        SpeciesIndividual point=head;
        while(point.next != null)
            point=point.next;
        point.next=species;
    }
}