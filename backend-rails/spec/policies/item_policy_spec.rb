describe ItemPolicy do
  subject { described_class }

  permissions :index? do
    let(:user) { create(:user) }

    it 'permits for everyone' do
      expect(subject).to permit(user, nil)
    end
  end

  describe 'Scope' do
    let(:user) { create(:user, items: user_items) }
    let!(:user_items) { create_list(:item, 1) }
    let!(:items2) { create_list(:item, 1) }

    let(:subject) { Pundit.policy_scope!(user, Item) }

    it 'returns items that belongs to the user' do
      expect(subject).to match_array(user_items)
    end
  end
end
