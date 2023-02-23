describe ItemPolicy do
  subject { described_class }

  permissions :index? do
    let(:user) { create(:user) }

    it 'permits for everyone' do
      expect(subject).to permit(user, nil)
    end
  end

  describe 'Scope' do
    let(:user) { create(:user, service_orders: user_service_orders) }
    let!(:user_service_orders) { create_list(:service_order, 1) }
    let!(:service_orders2) { create_list(:service_order, 1) }

    let(:subject) { Pundit.policy_scope!(user, ServiceOrder) }

    it 'returns service_orders that belongs to the user' do
      expect(subject).to match_array(user_service_orders)
    end
  end
end
