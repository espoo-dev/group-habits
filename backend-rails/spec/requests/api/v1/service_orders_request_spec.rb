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

  describe '#create' do
    let!(:user) { create(:user) }
    let!(:customer) { create(:customer, user:) }
    let!(:item) { create(:item, user:) }

    before do
      post api_v1_service_orders_path, params: create_service_order_params, headers: auth_headers, as: :json
    end

    context 'when data is valid' do
      let(:service_order_params) { attributes_for(:service_order, user_id: nil, customer_id: customer.id) }
      let(:items_ids) { { items_ids: [item.id] } }
      let(:creation_date) { { creation_date: '18/06/1991' } }

      let(:create_service_order_params) { service_order_params.merge(items_ids).merge(creation_date) }

      it 'returns status 201 created' do
        expect(response).to be_created
      end

      it 'returns item' do
        expect(json_response['id']).to_not be_nil
        expect(json_response['name']).to eq(create_service_order_params[:name])
        expect(json_response['creation_date']).to eq('18/06/1991')
        expect(json_response['items'][0]['id']).to eq(item.id)
      end
    end
  end

  describe '#update' do
    let!(:user) { create(:user) }
    let!(:customer) { create(:customer, user:) }
    let!(:item) { create(:item, user:) }
    let!(:service_order) { create(:service_order, user:) }

    before do
      put api_v1_service_order_path(service_order.id), params: update_service_order_params, headers: auth_headers,
                                                       as: :json
    end

    context 'when data is valid' do
      let(:service_order_params) { attributes_for(:service_order, user_id: nil, customer_id: customer.id) }
      let(:items_ids) { { items_ids: [item.id] } }

      let(:update_service_order_params) { service_order_params.merge(items_ids) }

      it 'returns status 200 ok' do
        expect(response).to be_ok
      end

      it 'returns item' do
        expect(json_response['id']).to_not be_nil
        expect(json_response['name']).to eq(update_service_order_params[:name])
        expect(json_response['items'][0]['id']).to eq(item.id)
      end
    end
  end

  describe '#destroy' do
    let(:user) { create(:user) }
    let!(:service_order) { create(:service_order, user:) }

    before { delete api_v1_service_order_path(service_order_id), headers: auth_headers, as: :json }

    describe 'when finds service_order' do
      let(:service_order_id) { service_order.id }
      it 'returns status 204 no_content' do
        expect(response).to be_no_content
      end

      it 'returns an empty hash' do
        expect(response.body).to eq('')
      end
    end

    describe 'when does not find service_order' do
      let(:service_order_id) { -1 }

      it 'returns status 404 not_found' do
        expect(response).to be_not_found
      end

      it 'returns proper error message' do
        expect(json_response['error']).to eq("Couldn't find ServiceOrder with 'id'=-1")
      end
    end
  end
end
