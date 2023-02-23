require 'rails_helper'

describe 'api/v1/service_orders', type: :request do
  describe '#index' do
    let(:user) { create(:user) }
    let!(:user_service_orders) { [service_order, service_order2] }
    let!(:service_order) { create(:service_order, name: 'abc', user:) }
    let!(:service_order2) { create(:service_order, name: 'def', user:) }
    let!(:service_orders2) { create_list(:service_order, 1) }

    context 'when not filtering' do
      before { get api_v1_service_orders_path, headers: auth_headers, as: :json }
      it 'returns status 200 ok' do
        expect(response).to be_successful
      end

      it "returns user's service_orders" do
        expect(json_response.pluck('id')).to match_array(user_service_orders.pluck(:id))
      end
    end

    context 'when filtering' do
      before do
        get api_v1_service_orders_path, params: filtering_params, headers: auth_headers, as: :json
      end

      context 'when filtering by name' do
        let(:filtering_params) { { name: 'bc' } }
        it 'returns status 200 ok' do
          expect(response).to be_successful
        end

        it "returns user's service_orders" do
          expect(json_response.pluck('id')).to match_array([service_order.id])
        end
      end
    end
  end
end
