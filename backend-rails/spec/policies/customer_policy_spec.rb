describe ItemPolicy do
  subject { described_class }

  permissions :index? do
    let(:user) { create(:user) }

    it 'permits for everyone' do
      expect(subject).to permit(user, nil)
    end
  end

  describe 'Scope' do
    let(:user) { create(:user, customers: user_customers) }
    let!(:user_customers) { create_list(:customer, 1) }
    let!(:items2) { create_list(:customer, 1) }

    let(:subject) { Pundit.policy_scope!(user, Customer) }

    it 'returns customers that belongs to the user' do
      expect(subject).to match_array(user_customers)
    end
  end
end
