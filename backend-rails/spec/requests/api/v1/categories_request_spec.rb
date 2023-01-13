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
end
