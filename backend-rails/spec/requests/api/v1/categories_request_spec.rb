require 'rails_helper'

describe 'api/v1/categories', type: :request do
  describe '#index' do
    let(:user) { create(:user, categories: user_categories) }
    let!(:user_categories) { create_list(:category, 1) }
    let!(:categories2) { create_list(:category, 1) }

    before { get api_v1_categories_path, headers: auth_headers, as: :json }

    it 'returns status 200 ok' do
      expect(response).to be_successful
    end

    it "returns user's categories" do
      expect(json_response.pluck('id')).to match_array(user_categories.pluck(:id))
    end
  end

  describe '#create' do
    let(:user) { create(:user) }
    let(:create_category_params) { attributes_for(:category, user_id: nil) }

    before do
      post api_v1_categories_path, params: create_category_params, headers: auth_headers, as: :json
    end

    it 'returns status 201 created' do
      expect(response).to be_created
    end

    it 'returns category' do
      expect(json_response['id']).to_not be_nil
      expect(json_response['name']).to eq(create_category_params[:name])
    end
  end
end
