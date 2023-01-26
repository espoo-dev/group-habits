require 'rails_helper'

describe 'api/v1/customers', type: :request do
  describe '#index' do
    let(:user) { create(:user) }
    let!(:user_customers) { [customer, customer2] }
    let!(:customer) { create(:customer, name: 'abc', customer_type: Customer.customer_types[:person], user:) }
    let!(:customer2) { create(:customer, name: 'def', customer_type: Customer.customer_types[:business], user:) }
    let!(:customers2) { create_list(:customer, 1) }

    context 'when not filtering' do
      before { get api_v1_customers_path, headers: auth_headers, as: :json }
      it 'returns status 200 ok' do
        expect(response).to be_successful
      end

      it "returns user's customers" do
        expect(json_response.pluck('id')).to match_array(user_customers.pluck(:id))
      end
    end

    context 'when filtering' do
      before do
        get api_v1_customers_path, params: filtering_params, headers: auth_headers, as: :json
      end

      context 'when filtering by name' do
        let(:filtering_params) { { name: 'bc' } }
        it 'returns status 200 ok' do
          expect(response).to be_successful
        end

        it "returns user's customers" do
          expect(json_response.pluck('id')).to match_array([customer.id])
        end
      end

      context 'when filtering by customer_type' do
        let(:filtering_params) { { customer_type: 'business' } }

        it 'returns status 200 ok' do
          expect(response).to be_successful
        end

        it "returns user's customers" do
          expect(json_response.pluck('id')).to match_array([customer2.id])
        end
      end
    end
  end

  describe '#create' do
    let(:user) { create(:user) }

    before do
      post api_v1_customers_path, params: create_customer_params, headers: auth_headers, as: :json
    end

    context 'when data is valid' do
      let(:create_customer_params) { attributes_for(:customer, user_id: nil, customer_type: 'business') }

      it 'returns status 201 created' do
        expect(response).to be_created
      end

      it 'returns customer' do
        expect(json_response['id']).to_not be_nil
        expect(json_response['name']).to eq(create_customer_params[:name])
      end
    end

    context 'when data is not valid' do
      let(:create_customer_params) { attributes_for(:customer, name: nil, user_id: nil) }

      it 'returns status 400 bad_request' do
        expect(response).to be_bad_request
      end

      it 'returns customer' do
        expect(json_response['error']).to eq("Validation failed: Name can't be blank")
      end
    end
  end
end
