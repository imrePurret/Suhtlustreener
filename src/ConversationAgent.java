import java.util.*;

import javax.swing.JOptionPane;

public class ConversationAgent {
	MotivationalSphere motivationalSphere = mainClass.motivationalSphere;
	
	public ConversationAgent() {

	}

	public void scales(String hinnatav) {
		List<Information> informationList = new InformationReader()
				.getInformationList();
		List<Answer> answerList = new AnswerReader().getAnswerList();
		List<String> zeroAnswers = new ArrayList();
		List<String> winAnswers = new ArrayList();
		int value = 0;
		for (Information information : informationList) {
			if (hinnatav.matches(".*(^| )" + information.getKeyWord() + ".{0,4}( |$).*")) {
				System.out.println(information.getKeyWord());
				value++;
				List<String> answers = new ArrayList();
				for (Answer answer : answerList) {
					if (answer.getAnswerIndex() == information.getAnswerIndex()) {
						answers.add(answer.getAnswer());
					}
				}
				renewMotivationalSphere(information.getSphereClass());
				if (procedureWish(motivationalSphere)
						|| procedureNeed(motivationalSphere)
						|| procedureMust(motivationalSphere)) {
					System.out.println("Soov võitis!");
					for (Answer answer : answerList) {
						if (answer.getAnswerIndex() == 9999) {
							winAnswers.add(answer.getAnswer());
						}
					}
					break;
				}
				if (!answering(answers)) {
					value = 0;
				}
			}
			if (value > 0) {
				System.out.println(motivationalSphere);
				break;
			}
			for (Answer answer : answerList) {
				if (answer.getAnswerIndex() == 0) {
					zeroAnswers.add(answer.getAnswer());
				}
			}
		}
		System.out.println(value);
		if (zeroAnswers.size() > 0 && value == 0) {
			answering(zeroAnswers);
		}
		if (winAnswers.size() > 0) {
			answering(winAnswers);
			mainClass.dialog.getTextField().setEditable(false);
			JOptionPane
					.showMessageDialog(
							null,
							"Teil õnnestus arvutit veenda taimetoitlaseks hakkama!\n"
									+ "Uue mängu alustamiseks valige menüüst alustaja ja seejärel "
									+ "\"Üldine\" ja \"Uus\".", "Õnnitlus!", 1);
		}

	}

	private void renewMotivationalSphere(String sphereClass) {
		if ("res".equals(sphereClass)) {
			motivationalSphere.setResources(1);
		}
		if ("ple".equals(sphereClass)) {
			motivationalSphere.setPleasant(1);
		}
		if ("unp".equals(sphereClass)) {
			motivationalSphere.setUnpleasant(1);
		}
		if ("use".equals(sphereClass)) {
			motivationalSphere.setUseful(1);
		}
		if ("har".equals(sphereClass)) {
			motivationalSphere.setHarmful(1);
		}
		if ("man".equals(sphereClass)) {
			motivationalSphere.setMandatory(1);
		}
		if ("pro".equals(sphereClass)) {
			motivationalSphere.setProhibited(1);
		}
		if ("pen".equals(sphereClass)) {
			motivationalSphere.setPenalty(1);
		}

	}

	private boolean answering(List<String> answers) {
		if (answers.size() > 0) {
			Random generator = new Random();
			int r = generator.nextInt(answers.size());
			mainClass.dialog.addTextToTextArea(answers.get(r));
			return true;
		}
		return false;
	}

	// Pleasant is bigger than unpleasant.
	public boolean procedureWish(MotivationalSphere motSphere) {
		if (motSphere.getPleasant() > motSphere.getUnpleasant()) {
			if (motSphere.getResources() == 0) {
				return false;
			}
			if (motSphere.getPleasant() > motSphere.getUnpleasant()
					+ motSphere.getHarmful()) {
				if (motSphere.getProhibited() == 0) {
					return true;
				} else {
					if (motSphere.getPleasant() > motSphere.getUnpleasant()
							+ motSphere.getHarmful() + motSphere.getPenalty()) {
						return true;
					}
					if (motSphere.getPleasant() + motSphere.getUseful() > motSphere
							.getUnpleasant()
							+ motSphere.getHarmful()
							+ motSphere.getPenalty()) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				if (motSphere.getPleasant() + motSphere.getUseful() > motSphere
						.getUnpleasant() + motSphere.getHarmful()) {
					if (motSphere.getProhibited() == 0) {
						return true;
					} else {
						if (motSphere.getPleasant() + motSphere.getUseful() > motSphere
								.getUnpleasant()
								+ motSphere.getHarmful()
								+ motSphere.getPenalty()) {
							return true;
						} else {
							return false;
						}
					}
				} else {
					if (motSphere.getMandatory() == 0) {
						return true;
					} else {
						if (motSphere.getPleasant() + motSphere.getUseful() + 1
								- motSphere.getPenalty() > motSphere
								.getHarmful() + motSphere.getPenalty()) {
							return true;
						} else {
							return false;
						}
					}
				}
			}
		}
		return false;
	}

	// Useful is bigger than harmful.
	public boolean procedureNeed(MotivationalSphere motShpere) {
		if (!(motShpere.getUseful()>motShpere.getHarmful())){
			return false;
		}
		if (motShpere.getResources() == 0) {
			return false;
		}
		if (motShpere.getPleasant() > motShpere.getUnpleasant()) {
			if (motShpere.getProhibited() == 0) {
				return true;
			}
			if (motShpere.getPleasant() + motShpere.getUseful() > motShpere
					.getUnpleasant()
					+ motShpere.getHarmful()
					+ motShpere.getPenalty()) {
				return true;
			} else {
				return false;
			}
		} else {
			if (motShpere.getMandatory() == 0) {
				return true;
			}
			if (motShpere.getPleasant() + motShpere.getUseful() + 1
					- motShpere.getPenalty() > motShpere.getUnpleasant()
					+ motShpere.getHarmful()) {
				return true;
			} else {
				return false;
			}
		}
	}

	// Activity is mandatory.
	public boolean procedureMust(MotivationalSphere motShpere) {
		if(motShpere.getMandatory()==0){
			return false;
		}
		if (motShpere.getResources() == 0) {
			return false;
		}
		if (motShpere.getPleasant() > motShpere.getUnpleasant()) {
			if (motShpere.getPleasant() + 1 - motShpere.getPenalty() > motShpere
					.getUnpleasant() + motShpere.getHarmful()) {
				return true;
			}
			if (motShpere.getPleasant() + motShpere.getUseful() + 1
					- motShpere.getPenalty() > motShpere.getUnpleasant()
					+ motShpere.getHarmful()) {
				return true;
			} else {
				return false;
			}

		} else {
			if (motShpere.getPleasant() + motShpere.getUseful() + 1
					- motShpere.getPenalty() > motShpere.getUnpleasant()) {
				return true;
			}
			if (motShpere.getPleasant() + motShpere.getUseful() + 1
					- motShpere.getPenalty() > motShpere.getUnpleasant()
					+ motShpere.getHarmful()) {
				return true;
			} else {
				return false;
			}
		}
	}

}
