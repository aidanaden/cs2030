class AlphaCoronavirus extends Virus {
    
    public AlphaCoronavirus(double probabilityOfMutating) {
        super("Alpha Coronavirus", probabilityOfMutating);
    }

	@Override
	public Virus spread(double random) {
		if (random <= super.getProbabilityOfMutating()) {
            return new SARS_CoV_2(super.getProbabilityOfMutating());
        } else {
            return new AlphaCoronavirus(super.getProbabilityOfMutating() * SimulationParameters.VIRUS_MUTATION_PROBABILITY_REDUCTION);
        }
    }
}
