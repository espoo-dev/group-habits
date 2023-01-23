require 'rails_helper'

describe 'api/v1/items', type: :request do
  describe '#index' do
    let(:user) { create(:user) }
    let!(:user_items) { [item, item2] }
    let!(:item) { create(:item, name: 'abc', user:) }
    let!(:item2) { create(:item, name: 'def', user:) }
    let!(:items2) { create_list(:item, 1) }

    context 'when not filtering' do
      before { get api_v1_items_path, headers: auth_headers, as: :json }
      it 'returns status 200 ok' do
        expect(response).to be_successful
      end

      it "returns user's items" do
        expect(json_response.pluck('id')).to match_array(user_items.pluck(:id))
      end
    end

    context 'when filtering' do
      before do
        get api_v1_items_path, params: { name: 'bc' }, headers: auth_headers, as: :json
      end
      it 'returns status 200 ok' do
        expect(response).to be_successful
      end

      it "returns user's items" do
        expect(json_response.pluck('id')).to match_array([item.id])
      end
    end
  end
end
