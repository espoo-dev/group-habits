describe CategoryPolicy do
  subject { described_class }

  permissions :index? do
    let(:user1) { create(:user) }

    it 'allow for everyone' do
      expect(subject).to permit(user1, nil)
    end
  end

  describe 'Scope' do
    let(:user) { create(:user, categories: user_categories) }
    let!(:user_categories) { create_list(:category, 1) }
    let!(:categories2) { create_list(:category, 1) }

    let(:subject) { Pundit.policy_scope!(user, Category) }

    it 'returns categories that belongs to the user' do
      expect(subject).to match_array(user_categories)
    end
  end
end
