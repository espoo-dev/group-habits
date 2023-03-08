require 'rails_helper'

describe 'api/v1/items', type: :request do
  describe '#index' do
    let(:user) { create(:user) }
    let!(:user_items) { [item, item2] }
    let!(:item) { create(:item, name: 'abc', item_type: 'product', user:) }
    let!(:item2) { create(:item, name: 'def', item_type: 'service', user:) }
    let!(:items2) { create_list(:item, 1) }

    context 'when not filtering' do
      before { get api_v1_items_path, headers: auth_headers, as: :json }
      it 'returns status 200 ok' do
        expect(response).to be_successful
      end

      it "returns user's items" do
        expect(json_response.pluck('id')).to match_array(user_items.pluck(:id))
      end

      it 'returns sale_price and purchase_price as float', :aggregate_failures do
        expect(json_response.pluck('sale_price')).to match_array(user_items.pluck(:sale_price))
        expect(json_response.pluck('purchase_price')).to match_array(user_items.pluck(:purchase_price))
      end
    end

    context 'when filtering' do
      before do
        get api_v1_items_path, params: filtering_params, headers: auth_headers, as: :json
      end

      context 'when filtering by name' do
        let(:filtering_params) { { name: 'bc' } }
        it 'returns status 200 ok' do
          expect(response).to be_successful
        end

        it "returns user's items" do
          expect(json_response.pluck('id')).to match_array([item.id])
        end
      end

      context 'when filtering by item_type' do
        let(:filtering_params) { { item_type: 'service' } }

        it 'returns status 200 ok' do
          expect(response).to be_successful
        end

        it "returns user's items" do
          expect(json_response.pluck('id')).to match_array([item2.id])
        end
      end
    end
  end

  describe '#create' do
    let(:user) { create(:user) }
    let(:category_id) { create(:category, user:).id }
    let(:sales_unit_id) { create(:sales_unit).id }

    before do
      post api_v1_items_path, params: create_item_params, headers: auth_headers, as: :json
    end

    context 'when data is valid' do
      let(:create_item_params) { attributes_for(:item, user_id: nil, category_id:) }

      it 'returns status 201 created' do
        expect(response).to be_created
      end

      it 'returns item' do
        expect(json_response['id']).to_not be_nil
        expect(json_response['name']).to eq(create_item_params[:name])
      end
    end

    context 'when data is not valid' do
      context 'when name is not valid' do
        let(:create_item_params) { attributes_for(:item, name: nil, user_id: nil, category_id:) }

        it 'returns status 400 bad_request' do
          expect(response).to be_bad_request
        end

        it 'returns item' do
          expect(json_response['error']).to eq("Validation failed: Name can't be blank")
        end
      end

      context 'when item_type is not valid' do
        let(:create_item_params) { attributes_for(:item, item_type: 'invalid item type', user_id: nil) }

        it 'returns status 400 bad_request' do
          expect(response).to be_bad_request
        end

        it 'returns item' do
          expect(json_response['error']).to eq('Validation failed: Item type is not included in the list')
        end
      end
    end
  end

  describe '#destroy' do
    let(:user) { create(:user) }
    let!(:item) { create(:item, user:) }

    before { delete api_v1_item_path(item_id), headers: auth_headers, as: :json }

    describe 'when finds item' do
      let(:item_id) { item.id }
      it 'returns status 204 no_content' do
        expect(response).to be_no_content
      end

      it 'returns an empty hash' do
        expect(response.body).to eq('')
      end
    end

    describe 'when does not find item' do
      let(:item_id) { -1 }

      it 'returns status 404 not_found' do
        expect(response).to be_not_found
      end

      it 'returns proper error message' do
        expect(json_response['error']).to eq("Couldn't find Item with 'id'=-1")
      end
    end
  end

  describe '#update' do
    let(:user) { create(:user) }
    let!(:item) { create(:item, user:) }

    before do
      put api_v1_item_path(item.id), params: update_item_params, headers: auth_headers,
                                     as: :json
    end

    context 'when data is valid' do
      let(:update_item_params) { { name: 'new_name' } }
      it 'returns status 200 ok' do
        expect(response).to be_successful
      end

      it 'updates item name' do
        expect(item.reload.name).to eq('new_name')
      end
    end

    context 'when data is not valid' do
      let(:update_item_params) { { name: nil } }
      it 'returns status 400 bad_request' do
        expect(response).to be_bad_request
      end
    end
  end
end
