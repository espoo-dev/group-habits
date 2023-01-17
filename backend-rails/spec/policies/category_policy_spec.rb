describe CategoryPolicy do
  subject { described_class }

  permissions :index? do
    let(:user) { create(:user) }

    it 'permits for everyone' do
      expect(subject).to permit(user, nil)
    end
  end

  permissions :create? do
    let(:user) { create(:user) }

    describe 'when user is owner' do
      let(:category) { build(:category, user:) }

      it 'permits' do
        expect(subject).to permit(user, category)
      end
    end

    describe 'when user is not owner' do
      let(:category) { build(:category) }

      it 'does not permit' do
        expect(subject).to_not permit(user, category)
      end
    end
  end

  permissions :destroy? do
    let(:user) { create(:user) }

    describe 'when user is owner' do
      let(:category) { build(:category, user:) }

      it 'permits' do
        expect(subject).to permit(user, category)
      end
    end

    describe 'when user is not owner' do
      let(:category) { build(:category) }

      it 'does not permit' do
        expect(subject).to_not permit(user, category)
      end
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
